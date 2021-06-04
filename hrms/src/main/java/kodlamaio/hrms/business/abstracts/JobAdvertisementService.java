package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getAll();
	
	Result add(JobAdvertisement jobAdvertisement);
	
	Result update(int jobAdvertisementId , JobAdvertisement jobAdvertisement);

	DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadlineAsc();

	DataResult<List<JobAdvertisement>> getByisActiveTrueAndEmployerId(int id);

	DataResult<List<JobAdvertisement>> findAllByIsActiveTrue();
}
