package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDetailDto;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertService {

	DataResult<List<JobAdvert>> getAll();
	
	Result add(JobAdvertDetailDto jobAdvertDetailDto);	
	Result Add(JobAdvert jobAdvert);
	Result update(JobAdvert jobAdvert);
	Result delete(int jobAdvertId);
	
	DataResult<List<JobAdvert>> getAllPagination(int pageNo);
	
	Result changeIsActiveByEmployee(int jobAdvertId);	
	Result changeIsOpenByEmployer(int jobAdvertId);
	
	DataResult<List<JobAdvert>> getAllByIsActiveByEmployee();// iş arayan	
	DataResult<List<JobAdvert>> getAllByIsActiveByEmployee_False();// admin
	
	DataResult<JobAdvert> getById(int id);	
	DataResult<List<JobAdvert>> getAllOpenJobAdvertList();
	DataResult<List<JobAdvert>> findAllByOrderByPublishedAt();
	DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id);
	DataResult<List<JobAdvert>> getAllByEmployerId(int employerId);

	DataResult<List<JobAdvert>> getByIsActiveTrueOrderByDeadlineAsc();

	DataResult<List<JobAdvert>> getByisActiveTrueAndEmployerId(int id);

	DataResult<List<JobAdvert>> findAllByIsActiveTrue();
	
	DataResult<List<JobAdvertDto>> getJobAdvertDetails();
	
	long countGetAll();
}
