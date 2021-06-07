package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;

public interface JobAdvertisementService {

	DataResult<List<JobAdvert>> getAll();
	
	Result add(JobAdvert jobAdvertisement);
	
	Result update(int jobAdvertisementId , JobAdvert jobAdvertisement);

	DataResult<List<JobAdvert>> getByIsActiveTrueOrderByApplicationDeadlineAsc();

	DataResult<List<JobAdvert>> getByisActiveTrueAndEmployerId(int id);

	DataResult<List<JobAdvert>> findAllByIsActiveTrue();
}
