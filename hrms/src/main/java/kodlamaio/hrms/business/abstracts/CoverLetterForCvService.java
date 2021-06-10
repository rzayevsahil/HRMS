package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.CoverLetterForCv;

public interface CoverLetterForCvService {
	
	Result add(CoverLetterForCv coverLetterForCv);
	Result update(CoverLetterForCv coverLetterForCv);
	Result delete(int id);
	DataResult<CoverLetterForCv> getById(int id);
	DataResult<List<CoverLetterForCv>> getAll();
}
