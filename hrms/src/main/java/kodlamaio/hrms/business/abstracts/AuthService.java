package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface AuthService {
	
	Result registerEmployer(Employer employer, String confirmPassword);
	Result registerJobseeker(JobSeeker jobseeker, String confirmPassword);
}
