package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;
	private EmployerDao employerDao;
	private CityDao cityDao;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao,EmployerDao employerDao,CityDao cityDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
	}
	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(),"data listelendi");
	}

	@Override
	public Result add(JobAdvert jobAdvertisement) {
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
	public Result update(int jobAdvertisementId, JobAdvert jobAdvertisement) {
Result result = BusinessRules.run(checkAplicationDeadline(jobAdvertisementId, jobAdvertisement));		
		if(result.isSuccess()) {	
			jobAdvertDao.save(jobAdvertisement);
			return new SuccessResult("JobAdvert updated");			
		}
		return result;
	}
	
	@Override
	public Result delete(int jobAdvertId) {
		this.jobAdvertDao.deleteById(jobAdvertId);
		return new SuccessResult("JobAdvert updated");
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

	

	//************************** Kurallar ***********************************
	
	private Result employerControl(int id) {
		if(!employerDao.existsById(id)) {
			return new ErrorResult("böyle bir kullanıcı yok");
		}
		return new SuccessResult();
	}
	
	private Result cityControl(int id) {
		if(!cityDao.existsById(id)) {
			return new ErrorResult("böyle bir şehir yok");
		}
		return new SuccessResult();
	}
	
		
	private Result salaryControl(JobAdvert jobAdvert) {
		if(jobAdvert.getSalaryMin()>=jobAdvert.getSalaryMax()) {
			return new ErrorResult("Minimum maaş maximum maaştan büyük veya eşit olamaz");
		}
		return new SuccessResult();
	}

private Result checkAplicationDeadline(int jobAdvertId, JobAdvert jobAdvert) {
		
		JobAdvert result = jobAdvertDao.findById(jobAdvertId).get();
		
		result.setActive(jobAdvert.isActive());
		
		
		
		if(jobAdvert.getDeadline().isBefore(LocalDateTime.now())) {
			return new ErrorResult("İlanın tarihi geçmiş");
		}
		return new SuccessResult();
	}


}
