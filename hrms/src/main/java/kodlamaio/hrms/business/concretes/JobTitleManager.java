package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;
	
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(JobTitle jobTitle) {
		Result result=BusinessRules.run(nullControl(jobTitle),titleRepeatControl(jobTitle));
		if (result.isSuccess()) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult("Başarıyla eklendi");
		}
		return result;
		
	}
	
	
	//******************************************* KURALLAR *******************************************
	
	private Result nullControl(JobTitle jobTitle) {
		if(jobTitle.getTitle()==null || jobTitle.getTitle().isEmpty()) {
			return new ErrorResult("Alanlar boş bırakılamaz");
		}
		return new SuccessResult();
	}
	
	private Result titleRepeatControl(JobTitle jobTitle) {
		if(jobTitleDao.findAllByTitle(jobTitle.getTitle()).stream().count()!=0) {
			return new ErrorResult("Bu pozisyon mevcut");
		}
		return new SuccessResult();
	}

}
