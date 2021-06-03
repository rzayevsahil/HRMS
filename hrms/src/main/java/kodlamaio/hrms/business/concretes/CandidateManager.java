package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(), "Data Listelendi");
	}

	@Override
	public Result add(Candidate candidate) {
		Result result = BusinessRules.run(identityNumberControl(candidate), nullControl(candidate),
				emailExist(candidate.getEmail()), emailFormatCheck(candidate.getEmail()),tcExist(candidate.getIdentificationNumber()));

		if (result.isSuccess()) {

			candidateDao.save(candidate);
			return new SuccessResult("Eklendi");
		}
		return result;
	}
	
	
	//******************************************* KURALLAR *******************************************

	private Result identityNumberControl(Candidate candidate) {

		if (candidate.getIdentificationNumber().length() != 11) {
			return new ErrorResult("Tc numarası 11 haneli olmalıdır!");
		}
		return new SuccessResult();
	}

	private Result nullControl(Candidate candidate) {
		if (candidate.getEmail().isEmpty() || candidate.getPassword().isEmpty() || candidate.getBirthDate()==null
				|| candidate.getFirstName().isEmpty() || candidate.getLastName().isEmpty()
				|| candidate.getIdentificationNumber().isEmpty()) {

			return new ErrorResult("Alanlar boş bırakılamaz");
		}
		return new SuccessResult();
	}

	private Result emailExist(String email) {
		if (candidateDao.findAllByEmail(email).stream().count() != 0) {
			return new ErrorResult("bu email mevcut");
		}
		return new SuccessResult();
	}
	
	private Result tcExist(String identificationNumber) {
		if (candidateDao.findAllByIdentificationNumber(identificationNumber).stream().count() != 0) {
			return new ErrorResult("Bu tc mevcut");
		}
		return new SuccessResult();
	}

	public static final Pattern validEmail = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$",
			Pattern.CASE_INSENSITIVE);

	private Result emailFormatCheck(String email) {
		if (!validEmail.matcher(email).find()) {
			return new ErrorResult("Geçerli bir mail adresi girin!");
		}
		return new SuccessResult();
	}
}
