package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {

	DataResult<List<JobSeeker>> getAll();
	DataResult<JobSeeker> getById(int id);
	DataResult<JobSeeker> getJobseekerByNationalId(String nationalId);	
	//DataResult<JobSeekerCvDto> getJobseekerCVById(int id);

	Result add(JobSeeker candidate);
	Result update(JobSeeker jobseeker);
	Result delete(int id);
}
