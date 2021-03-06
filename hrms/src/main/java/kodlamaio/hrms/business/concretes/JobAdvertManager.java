package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertConfirmationDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkHourDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkTypeDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDetailDto;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	private WorkTypeDao workTypeDao;
	private WorkHourDao workHourDao;
	private JobPositionDao jobPositionDao;
	private JobAdvertConfirmationDao jobAdvertConfirmationDao;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao, EmployerDao employerDao, CityDao cityDao,
			WorkTypeDao workTypeDao, WorkHourDao workHourDao, JobPositionDao jobPositionDao,
			JobAdvertConfirmationDao jobAdvertConfirmationDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.workTypeDao = workTypeDao;
		this.workHourDao = workHourDao;
		this.jobPositionDao = jobPositionDao;
		this.jobAdvertConfirmationDao = jobAdvertConfirmationDao;
	}


	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(),"Data listelendi");
	}

	@Override
	public Result Add(JobAdvert jobAdvertisement) {
		Result result = BusinessRules.run( employerControl(jobAdvertisement.getEmployer().getId()),
				cityControl(jobAdvertisement.getCity().getId()),				
				salaryControl(jobAdvertisement));		
		if(result.isSuccess()) {				
			jobAdvertDao.save(jobAdvertisement);
			return new SuccessResult("JobAdvert added");			
		}
		return result;
	}

	@Override
	public Result update(JobAdvert jobAdvertisement) {
			jobAdvertDao.save(jobAdvertisement);
			return new SuccessResult("JobAdvert updated");	
	}
	
	@Override
	public Result delete(int jobAdvertId) {
		this.jobAdvertDao.deleteById(jobAdvertId);
		return new SuccessResult("JobAdvert deleted");
	}

	@Override
	public DataResult<List<JobAdvert>> getByIsActiveTrueOrderByDeadlineAsc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByIsActiveTrueOrderByDeadlineAsc(),"Data listelendi");
	}

	@Override
	public DataResult<List<JobAdvert>> getByisActiveTrueAndEmployerId(int id) {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByIsActiveTrueAndEmployer_Id(id));
	}

	@Override
	public DataResult<List<JobAdvert>> findAllByIsActiveTrue() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.findAllByIsActiveTrue());
	}
	
	@Override
	public DataResult<List<JobAdvertDto>> getJobAdvertDetails() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getJobAdvertDetails());
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------
	
	@Override
	public Result add(JobAdvertDetailDto jobAdvertDetailDto) {
		
		JobAdvert jobAdvert = new JobAdvert();
		//LocalDate dateTime=LocalDate.now();
		jobAdvert.setCity(this.cityDao.getById(jobAdvertDetailDto.getCityId()));
		jobAdvert.setJobPosition(this.jobPositionDao.getById(jobAdvertDetailDto.getJobPositionId()));
		jobAdvert.setWorkHour(this.workHourDao.getById(jobAdvertDetailDto.getWorkHourId()));
		jobAdvert.setWorkType(this.workTypeDao.getById(jobAdvertDetailDto.getWorkTypeId()));
		jobAdvert.setDescription(jobAdvertDetailDto.getDescription());
		jobAdvert.setSalaryMax(jobAdvertDetailDto.getMaxSalary());
		jobAdvert.setSalaryMin(jobAdvertDetailDto.getMinSalary());
		jobAdvert.setOpenPositionCount(jobAdvertDetailDto.getOpenPositionCount());
		jobAdvert.setEmployer(this.employerDao.getById(jobAdvertDetailDto.getEmployerId()));
		jobAdvert.setDeadline(jobAdvertDetailDto.getDeadLine());
		
		//bunlar zaten default olarak geliyor
		/*jobAdvert.setOpen(true);
		jobAdvert.setDeleted(false);
		jobAdvert.setCreatedAt(jobAdvert.getCreatedAt());
		jobAdvert.setPublishedAt(dateTime);
		jobAdvert.setActive(true);*/
	
		Result result = BusinessRules.run(CheckIfNullField(jobAdvert),salaryControl(jobAdvert));		
		if(result.isSuccess()) {				
			this.jobAdvertDao.save(jobAdvert);
			return new SuccessResult("Ba??ar?? ile eklendi");			
		}
		return result;
		
		
		//JobAdvertConfirmation jobAdvertConfirmation = new JobAdvertConfirmation();
		
		
	}
	


	@Override
	public Result changeIsActiveByEmployee(int jobAdvertId) {
		
		// sadece true'ya ??ekmek i??in 
		JobAdvert jobAdvertIsActiveEmployee= this.jobAdvertDao.findById(jobAdvertId);
		jobAdvertIsActiveEmployee.setActive(!jobAdvertIsActiveEmployee.isActive());
		this.jobAdvertDao.save(jobAdvertIsActiveEmployee);
		return new SuccessResult("???? ilan??n??n admin taraf??ndan aktifli??i de??i??tirildi");
	}



	@Override
	public Result changeIsOpenByEmployer(int jobAdvertId) {
		
		//???? verenin isOpen'?? de??i??tirince aktivlik direk false oluyor ve onu yeniden open yapsa bile o false da kal??yor
		//????nk?? i?? veren yeniden kapatt?????? ilan?? tekrar a??mas?? sanki yeniden yeni bir i?? ilan?? eklemesi demek oluyor
		//ve bu ilan yeniden sistem personeli yani employee taraf??nda do??rulanmal??
		JobAdvert jobAdvertToChangeIsOpen =this.jobAdvertDao.findById(jobAdvertId);
		jobAdvertToChangeIsOpen.setOpen(!jobAdvertToChangeIsOpen.isOpen());
		jobAdvertToChangeIsOpen.setActive(false);
		/*if (jobAdvertToChangeIsOpen.isOpen()) {
			this.jobAdvertDao.save(jobAdvertToChangeIsOpen);
			return new SuccessResult("???? ilan??n??n i?? veren taraf??ndan taraf??ndan aktifli??i de??i??tirildi");
		}*/
		this.jobAdvertDao.save(jobAdvertToChangeIsOpen);
		return new SuccessResult("???? ilan??n??n i?? veren taraf??ndan taraf??ndan aktifli??i de??i??tirildi");
		
		
	}



	@Override
	public DataResult<List<JobAdvert>> getAllByIsActiveByEmployee() {
		
		// BURASI A??IK ???? ??LANLARI VE DO??RULANMI?? ???? ??LANLARININ G??Z??KT?????? KISIM
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByIsActiveByEmployee());
	}



	@Override
	public DataResult<List<JobAdvert>> getAllByIsActiveByEmployee_False() {
		
		// A????k olan i?? ilanlar??n?? admin g??recek sadece kendi sisteminden onaylamak i??in
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByIsActiveByEmployee_False()) ;
	}



	@Override
	public DataResult<JobAdvert> getById(int id) {
			return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.findById(id),"Data listelendi");
	}



	@Override
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertList() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertList());
	}



	@Override
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedAt() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByOrderByPublishedAtDesc());
	}



	@Override
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertByEmployer(id));
	}



	@Override
	public DataResult<List<JobAdvert>> getAllByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByEmployerId(employerId));
	}
	
	@Override
	public DataResult<List<JobAdvert>> getAllPagination(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo-1, 10);
	
		//return  new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(pageable).getContent(),"Ba??ar??l??");
		return  new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByIsActiveTrueAndIsOpenTrueOrderByDeadlineDesc(pageable));
	}

	
	@Override
	public long countGetAll() {
		return this.jobAdvertDao.count();
	}
	

	//************************** Kurallar ***********************************
	
	private Result employerControl(int id) {
		if(!employerDao.findAll().stream().equals(id)) {
			return new ErrorResult("b??yle bir kullan??c?? yok");
		}
		return new SuccessResult();
	}
	private Result cityControl(int id) {
		if(!cityDao.existsById(id)) {
			return new ErrorResult("b??yle bir ??ehir yok");
		}
		return new SuccessResult();
	}
	
		
	private Result salaryControl(JobAdvert jobAdvert) {
		if(jobAdvert.getSalaryMin()>=jobAdvert.getSalaryMax()) {
			return new ErrorResult("Minimum maa?? maximum maa??tan b??y??k veya e??it olamaz");
		}
		return new SuccessResult();
	}
	
	
	private Result CheckIfNullField(JobAdvert jobAdvert) {
		if (jobAdvert.getJobPosition().getId() != 0 && !jobAdvert.getDescription().isEmpty() && jobAdvert.getCity().getId() != 0
				&& jobAdvert.getOpenPositionCount() != 0) {
			return new SuccessResult();
		}
		return new ErrorResult("Alanlar bo?? olamaz");
	}



	
	
	/*private Result nullEmployerId(int employerId) {
		if (!employerDao.findAll().stream().equals(employerDao.getById(employerId))) {
			return new ErrorResult("This EmployerId doesn't find");
		}
		return new SuccessResult();
	}
	
	private Result nullCityId(int cityId) {
		if (!cityDao.findAll().stream().equals(cityDao.getById(cityId))) {
			return new ErrorResult("This CityId doesn't find");
		}
		return new SuccessResult();
	}
	
	private Result nullJobAdvertId(int jobAdvertId) {
		if (!jobAdvertDao.findAll().stream().equals(cityDao.getById(jobAdvertId))) {
			return new ErrorResult("This JobAdvertId doesn't find");
		}
		return new SuccessResult();
	}*/

	

 	/*private Result checkAplicationDeadline(int jobAdvertId, JobAdvert jobAdvert) {
		
		JobAdvert result = jobAdvertDao.findById(jobAdvertId).get();
		
		result.setActive(jobAdvert.isActive());
		
		
		
		if(jobAdvert.getDeadline().isBefore(LocalDateTime.now())) {
			return new ErrorResult("??lan??n tarihi ge??mi??");
		}
		return new SuccessResult();
	}*/


}
