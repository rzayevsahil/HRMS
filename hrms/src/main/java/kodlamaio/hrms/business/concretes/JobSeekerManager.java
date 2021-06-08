package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobseekerDao;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobseekerDao) {
		super();
		this.jobseekerDao = jobseekerDao;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(jobseekerDao.findAll(), "Data Listelendi");
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		Result result = BusinessRules.run(emailExist(jobSeeker.getEmail()),tcExist(jobSeeker.getNationalId()));

		if (result.isSuccess()) {
			jobseekerDao.save(jobSeeker);
			return new SuccessResult("Eklendi");
		}
		return result;
	}
	
	@Override
	public Result update(JobSeeker jobseeker) {
		this.jobseekerDao.save(jobseeker);
		return new SuccessResult("Jobseeker updated.");
	}

	@Override
	public Result delete(int id) {
		this.jobseekerDao.deleteById(id);
		return new SuccessResult("Jobseeker deleted.");
	}
	
	@Override
	public DataResult<JobSeeker> getById(int id) {
		return new SuccessDataResult<JobSeeker>(this.jobseekerDao.getById(id));
	}

	@Override
	public DataResult<JobSeeker> getJobseekerByNationalId(String nationalId) {
		return new SuccessDataResult<JobSeeker>(this.jobseekerDao.findJobseekerByNationalId(nationalId));
	}

	
	
	//******************************************* KURALLAR *******************************************

	private Result emailExist(String email) {
		if (jobseekerDao.findAllByEmail(email).stream().count() != 0) {
			return new ErrorResult("This Email is available");
		}
		return new SuccessResult();
	}
	
	private Result tcExist(String nationalId) {
		if (jobseekerDao.findAllByNationalId(nationalId).stream().count() != 0) {
			return new ErrorResult("This TC is available");
		}
		return new SuccessResult();
	}

	

	
}
