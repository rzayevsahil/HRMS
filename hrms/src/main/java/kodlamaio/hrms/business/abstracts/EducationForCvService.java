package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.EducationForCv;

public interface EducationForCvService {
	
	Result add(EducationForCv educationForCv);
	Result update(EducationForCv educationForCv);
	Result delete(int id);

	DataResult<List<EducationForCv>> getAll();
	DataResult<EducationForCv>getById(int id);
	DataResult<List<EducationForCv>> getByJobSeekerIdOrderByGraduationYearDesc(int id);
	DataResult<List<EducationForCv>> getAllByJobSeekerId(int id);
}
