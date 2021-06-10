package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.LanguageForCv;

public interface LanguageForCvService {
	
	Result add(LanguageForCv languageForCv);
	Result update(LanguageForCv languageForCv);
	Result delete(int id);
	
	DataResult<LanguageForCv> getById(int id);
	DataResult<List<LanguageForCv>> getAll();
	DataResult<List<LanguageForCv>>getAllByJobSeekerId(int id);
}
