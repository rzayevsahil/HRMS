package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.ExperienceForCv;

public interface ExperienceForCvService {
	Result add(ExperienceForCv experienceForCv);
	Result update(ExperienceForCv experienceForCv);
	Result delete(int id);
	
	DataResult<ExperienceForCv> getById(int id);
	DataResult<List<ExperienceForCv>> getAll();
	DataResult<List<ExperienceForCv>> getAllByJobSeekerIdOrderByLeaveDateDesc(int id);
	DataResult<List<ExperienceForCv>> getAllByJobSeekerId(int id);
}
