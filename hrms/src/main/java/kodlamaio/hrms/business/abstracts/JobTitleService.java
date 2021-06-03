package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobTitle;


public interface JobTitleService {
	
	DataResult<List<JobTitle>> getAll();
	
	Result add(JobTitle jobTitle);
}
