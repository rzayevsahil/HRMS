package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertService {

	DataResult<List<JobAdvert>> getAll();
	
	Result add(JobAdvert jobAdvertisement);	
	Result update(JobAdvert jobAdvertisement);
	Result delete(int jobAdvertisementId);

	DataResult<List<JobAdvert>> getByIsActiveTrueOrderByDeadlineAsc();

	DataResult<List<JobAdvert>> getByisActiveTrueAndEmployerId(int id);

	DataResult<List<JobAdvert>> findAllByIsActiveTrue();
	
	DataResult<List<JobAdvertDto>> getJobAdvertDetails();
}
