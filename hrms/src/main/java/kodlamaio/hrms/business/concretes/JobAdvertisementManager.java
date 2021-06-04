package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	private CityDao cityDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,EmployerDao employerDao,CityDao cityDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(),"data listelendi");
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		Result result = BusinessRules.run( employerControl(jobAdvertisement.getEmployer().getId()),
				cityControl(jobAdvertisement.getCity().getId()),
				nullControl(jobAdvertisement),
				minSalaryControl(jobAdvertisement),
				quotaControl(jobAdvertisement),
				salaryControl(jobAdvertisement));
		
		if(result.isSuccess()) {	
			
			jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("eklendi");
			
		}
		return result;
	}

	@Override
	public Result update(int jobAdvertisementId, JobAdvertisement jobAdvertisement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadlineAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActiveTrueOrderByApplicationDeadlineAsc(),"Data listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployerId(int id) {
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.getByisActiveTrueAndEmployer_Id(id));
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAllByIsActiveTrue());
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
	
	private Result nullControl(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return new ErrorResult("iş tanımı alanı boş olamaz");
		}
		else if(jobAdvertisement.getMinSalary() ==null || jobAdvertisement.getMaxSalary() == null) {
			return new ErrorResult("maaş alanları doldurulmalıdır");
		}
		else if(jobAdvertisement.getApplicationDeadline() == null) {
			return new ErrorResult("son başvuru tarihi boş bırakılamaz");
		}
		
		return new SuccessResult();
	}
	
	private Result minSalaryControl(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary()<3000) {
			return new ErrorResult("minimum maaş 3000 tl'den az olamaz");
		}
		return new SuccessResult();
	}
	
	private Result quotaControl(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getQuota()<1) {
			return new ErrorResult("açık pozisyon adedi 1'den küçük olamaz");
		}
		return new SuccessResult();
	}
	
	private Result salaryControl(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary()>jobAdvertisement.getMaxSalary()) {
			return new ErrorResult("Minimum maaş maximum maaştan büyük olamaz");
		}else if(jobAdvertisement.getMinSalary().equals(jobAdvertisement.getMaxSalary())) {
			return new ErrorResult("Minimum maaş maximum maaşa eşit olamaz");
		}
		return new SuccessResult();
	}
	



}
