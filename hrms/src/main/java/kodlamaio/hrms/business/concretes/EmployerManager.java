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
		
		Result result = BusinessRules.run(emailExist(employer.getEmail()), nullControl(employer),checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebAddress()));

		if (result.isSuccess()) {
			employerDao.save(employer);
			return new SuccessResult("eklendi");
		}
		return result;
	}

	
	//******************************************* KURALLAR *******************************************
	

	private Result emailExist(String email) {
		if (employerDao.findAllByEmail(email).stream().count() != 0) {
			return new ErrorResult("bu email mevcut");
		}
		return new SuccessResult();
	}

	private Result nullControl(Employer employer) {
		if (employer.getEmail().equals("") || employer.getPassword().isEmpty()
				|| employer.getCompanyName().equals("") || employer.getWebAddress().equals("")) {

			return new ErrorResult("bu alanlar boş bırakılamaz");
		}
		return new SuccessResult();
	}

	private Result checkIfEqualEmailAndDomain(String email, String website) {

		String[] emailArr = email.split("@", 2); // @ gördüğünde böler 2 ayrı parçaya ve dizide tuttuk
		String domain = website.substring(4, website.length()); // 4. karakterden başlayıp website uzunluğu kadar alır
		// System.out.println(domain);

		if (emailArr[1].equals(domain)) {
			return new SuccessResult("Domain eklendi");
		}
		return new ErrorResult("Domain hatalı");
	}

}
