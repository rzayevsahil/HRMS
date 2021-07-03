package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.VerificationEmployer;

public interface VerificationEmployerService {
	Result add(VerificationEmployer verificationEmployer);

	Result delete(int id);

	DataResult<List<VerificationEmployer>> getAll();
	
	DataResult<VerificationEmployer> getById(int id);
	
	DataResult<List<VerificationEmployer>> getAllByVerifyFalse();
}
