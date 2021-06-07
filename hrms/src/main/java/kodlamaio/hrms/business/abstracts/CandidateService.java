package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface CandidateService {

	DataResult<List<JobSeeker>> getAll();

	Result add(JobSeeker candidate);
}
