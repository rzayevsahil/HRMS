package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;
	
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		Result result=BusinessRules.run(positionRepeatControl(jobPosition));
		if (result.isSuccess()) {
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("Başarıyla eklendi");
		}
		return result;
		
	}
	
	@Override
	public Result update(JobPosition jobPosition) {
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("JobPosition updated");
	}

	@Override
	public Result delete(int id) {
		this.jobPositionDao.deleteById(id);;
		return new SuccessResult("JobPosition deleted");
	}

	@Override
	public DataResult<JobPosition> getById(int id) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.getOne(id));
	}

	@Override
	public DataResult<JobPosition> getJobByTitle(String title) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByJobPosition(title));
	}
	
	
	//******************************************* KURALLAR *******************************************
	
	
	private Result positionRepeatControl(JobPosition jobPosition) {
		if(jobPositionDao.findAllByJobPosition(jobPosition.getJobPosition()).stream().count()!=0) {
			return new ErrorResult("This Position is available");
		}
		return new SuccessResult();
	}

}
