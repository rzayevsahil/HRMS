package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationEmployerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationEmployerDao;
import kodlamaio.hrms.entities.concretes.VerificationEmployer;

@Service
public class VerificationEmployerManager implements VerificationEmployerService{

	private VerificationEmployerDao verificationEmployerDao;

	@Autowired
	public VerificationEmployerManager(VerificationEmployerDao verificationEmployerDao) {
		super();
		this.verificationEmployerDao = verificationEmployerDao;
	}

	@Override
	public Result add(VerificationEmployer verificationEmployer) {
		this.verificationEmployerDao.save(verificationEmployer);
		return new SuccessResult("verificationEmployer eklendi");
	}

	@Override
	public Result delete(int id) {
		this.verificationEmployerDao.deleteById(id);
		return new SuccessResult("Silindi");
	}

	@Override
	public DataResult<List<VerificationEmployer>> getAll() {
		return new SuccessDataResult<List<VerificationEmployer>>(this.verificationEmployerDao.findAll());
	}

	@Override
	public DataResult<VerificationEmployer> getById(int id) {
		return new SuccessDataResult<VerificationEmployer>(this.verificationEmployerDao.getById(id));
	}

	@Override
	public DataResult<List<VerificationEmployer>> getAllByVerifyFalse() {
		return new SuccessDataResult<List<VerificationEmployer>>(this.verificationEmployerDao.getAllByVerifyFalse());
	}

	@Override
	public Result changeIsVerifiedByEmployee(int verificationEmployerId) {
		VerificationEmployer verificationEmployer=this.verificationEmployerDao.getById(verificationEmployerId);
		verificationEmployer.setVerified(true);
		this.verificationEmployerDao.save(verificationEmployer);
		return new SuccessResult("false yapıldı");
	}

	@Override
	public long countGetAll() {
		return this.verificationEmployerDao.count();
	}
	
	
	
}
