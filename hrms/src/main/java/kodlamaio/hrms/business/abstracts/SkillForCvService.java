package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.SkillForCv;

public interface SkillForCvService {
	
	Result add(SkillForCv skillForCv);
	Result delete(int id);
	Result update(SkillForCv skillForCv);

	DataResult<SkillForCv> getById(int id);
	DataResult<List<SkillForCv>> getAll();
	DataResult<List<SkillForCv>> getAllByJobSeekerId(int id);
}
