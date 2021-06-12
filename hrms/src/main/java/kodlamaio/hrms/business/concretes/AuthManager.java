package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.adapters.mernis.MernisService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.verification.VerificationService;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.VerificationCode;

@Service
public class AuthManager implements AuthService{

	private UserService userService;
	private EmployerService employerService;
	private JobSeekerService jobseekerService;
	private VerificationService verificationService;
	private MernisService mernisService;
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public AuthManager(UserService userService, EmployerService employerService, JobSeekerService jobseekerService,
			VerificationService verificationService, MernisService mernisService,
			VerificationCodeService verificationCodeService) {
		super();
		this.userService = userService;
		this.employerService = employerService;
		this.jobseekerService = jobseekerService;
		this.verificationService = verificationService;
		this.mernisService = mernisService;
		this.verificationCodeService = verificationCodeService;
	}
	
	

	@Override
	public Result registerEmployer(Employer employer, String confirmPassword) {
		
		Result result=BusinessRules.run(checkIfNullInfoForEmployer(employer),
				checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebsite()),
				checkIfEmailExists(employer.getEmail()),
				checkIfEqualPasswordAndConfirmPassword(employer.getPassword(), confirmPassword));
		
		if (result.isSuccess()) {
			
			employerService.add(employer);
			String code = verificationService.sendCode();
			verificationCodeRecord(code, employer.getId(), employer.getEmail());
			return new SuccessResult("Registration has been successfully completed");
		}
		
		return result;
	}

	@Override
	public Result registerJobseeker(JobSeeker jobseeker, String confirmPassword) {
		
		Result result=BusinessRules.run(checkIfRealPerson(jobseeker.getNationalId(),jobseeker.getFirstName(),
				jobseeker.getLastName(), jobseeker.getDateOfBirth().getYear()),
				checkIfNullInfoForJobseeker(jobseeker, confirmPassword),checkIfExistsTcNo(jobseeker.getNationalId()),
				checkIfEmailExists(jobseeker.getEmail()));
		
		if (result.isSuccess()) {
			jobseekerService.add(jobseeker);
			String code = verificationService.sendCode();
			verificationCodeRecord(code, jobseeker.getId(), jobseeker.getEmail());
			return new SuccessResult("Registration has been successfully completed");
		}
		
		return result;
	}
	
	
	
	//******************************************* KURALLAR *******************************************
	
	private Result checkIfNullInfoForEmployer(Employer employer) {
		
		// Bütün alanların boş olup olmadığını kontrol ediyor

		if (employer.getCompanyName() != null && employer.getWebsite() != null && employer.getEmail() != null
				&& employer.getPhoneNumber() != null && employer.getPassword() != null) {

			return new SuccessResult();
		}

		return new ErrorResult("The fields cannot be empty");
	}
	
	
	private Result checkIfEqualEmailAndDomain(String email, String website) {
		String[] emailArr = email.split("@", 2);
		String domain = website.substring(4, website.length());

		if (emailArr[1].equals(domain)) {

			return new SuccessResult();
		}

		return new ErrorResult("Invalid email address");
	}

	
	private Result checkIfNullInfoForJobseeker(JobSeeker jobseeker, String confirmPassword) {
		
		// iş arayanların fieldlarını boş olup olmamasına göre kontrol ediyor
		
		if (jobseeker.getFirstName() != null && jobseeker.getLastName() != null && jobseeker.getNationalId() != null
				&& jobseeker.getDateOfBirth() != null && jobseeker.getPassword() != null && jobseeker.getEmail() != null
				&& confirmPassword != null) {

			return new SuccessResult();
		}
		
		return new ErrorResult("You have entered missing information. Please fill in all fields.");
	}

	private Result checkIfExistsTcNo(String nationalId) {
		
		// NationalId yi kontrol ediyor varsa eklemiycek 
		
		if (this.jobseekerService.getJobseekerByNationalId(nationalId).getData() == null) {
			return new SuccessResult();
		}
		
		return new ErrorResult("This NationalId is available.");
	}
	

	private Result checkIfRealPerson(String nationalId, String firstName, String lastName, int yearOfBirth) {

		if (mernisService.checkIfRealPerson(nationalId, firstName, lastName, yearOfBirth)) {
			return new SuccessResult();
		}
		
		return new ErrorResult("User could not be verified.");
	}
	
	
	private Result checkIfEmailExists(String email) {
		
	// emaili kontrol ediyor eğer email varsa ekleme olmıycak
		
		if (this.userService.getUserByEmail(email).getData() == null) {

			return new SuccessResult();
		}

		return new ErrorResult("This Email is available");
	}
	
	
	private Result checkIfEqualPasswordAndConfirmPassword(String password, String confirmPassword) {
		
		// password ve doğrulama passwordunun aynı olup olmadığını kontrol ediyor
		
		if (!password.equals(confirmPassword)) {
			
			return new ErrorResult("Passwords do not match.");
		}

		return new SuccessResult();
	}
	
			
	private void verificationCodeRecord(String code, int id, String email) {
				
		VerificationCode verificationCode = new VerificationCode(id, code, false, LocalDateTime.now());
		this.verificationCodeService.add(verificationCode);
		System.out.println("Verification code has been sent to " + email );
			
	}

}
