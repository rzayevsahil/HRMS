package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;

	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Data listelendi");
	}

	@Override
	public Result add(Employer employer) {
		
		Result result = BusinessRules.run(emailExist(employer.getEmail()),checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebsite()));

		if (result.isSuccess()) {
			employerDao.save(employer);
			return new SuccessResult("Employer added");
		}
		return result;
	}

	@Override
	public Result update(Employer employer) {
		Result result = BusinessRules.run(emailExist(employer.getEmail()),checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebsite()));

		if (result.isSuccess()) {
			employerDao.save(employer);
			return new SuccessResult("Employer update");
		}
		return result;
	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
		return new SuccessResult("Employer deleted");
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(this.employerDao.getById(id),"Data listelendi");
	}
	
	
	
	@Override
	public DataResult<List<Employer>> getAllByVerify() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllByVerify());
	}

	@Override
	public Result changeIsVerifiedByEmployee(int employerId) {
		Employer employer = this.employerDao.getById(employerId);
		employer.setVerified(!employer.isVerified());
		this.employerDao.save(employer);
		return new SuccessResult("Şirketin Doğrulanma durumu değiştirildi");
	}

	@Override
	public long countById(int id) {
		return this.employerDao.countById(id);
	}

	@Override
	public long countGetAll() {
		return this.employerDao.count();
	}

	
	//******************************************* KURALLAR *******************************************
	

	private Result emailExist(String email) {
		if (employerDao.findAllByEmail(email).stream().count() != 0) {
			return new ErrorResult("This Email is available");
		}
		return new SuccessResult();
	}
	
	private Result checkIfEqualEmailAndDomain(String email, String website) {

		String[] emailArr = email.split("@", 2); // @ gördüğünde böler 2 ayrı parçaya ve dizide tuttuk
		String domain = website.substring(4, website.length()); // 4. karakterden başlayıp website uzunluğu kadar alır
		// System.out.println(domain);
		//www.kodlamaio.com - kullanıcıismi@kodlamaio.com
		if (emailArr[1].equals(domain)) {
			return new SuccessResult("Domain added");
		}
		return new ErrorResult("Domain is wrong");
	}

}
