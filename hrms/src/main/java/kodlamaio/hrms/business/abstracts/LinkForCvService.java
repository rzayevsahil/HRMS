package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.LinkForCv;

public interface LinkForCvService {
	
	Result add(LinkForCv linkForCv);
	Result update(LinkForCv linkForCv);
	Result delete(int id);
	
	DataResult<List<LinkForCv>> getAll();
	DataResult<LinkForCv> getById(int id);
	DataResult<List<LinkForCv>> getAllByJobSeekerId(int id);
}
