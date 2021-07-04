package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;


public interface JobPositionService {
	
	DataResult<List<JobPosition>> getAll();
	
	Result add(JobPosition jobPosition);
	Result update(JobPosition jobPosition);
	Result delete(int id);
	
	DataResult<JobPosition> getById(int id);
	DataResult<JobPosition> getJobByTitle(String title);
	
	long countGetAll();
}
