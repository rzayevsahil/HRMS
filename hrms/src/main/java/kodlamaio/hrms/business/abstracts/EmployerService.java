package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	DataResult<List<Employer>> getAll(); 
	DataResult<Employer> getById(int id);
	
	DataResult<List<Employer>> getAllByVerify();
	Result changeIsVerifiedByEmployee(int employerId);
	
	long countById(int id);
	long countGetAll();

	
	Result add(Employer employer);
	Result update(Employer employer);
	Result delete(int id);
}
