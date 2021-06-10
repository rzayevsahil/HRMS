package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageForCvDao;
import kodlamaio.hrms.entities.concretes.LanguageForCv;

@Service
public class LanguageForCvManager implements LanguageForCvService {

	private LanguageForCvDao languageForCvDao;
	
	@Autowired
	public LanguageForCvManager(LanguageForCvDao languageForCvDao) {
		super();
		this.languageForCvDao = languageForCvDao;
	}
	
	@Override
	public Result add(LanguageForCv languageForCv) {
		this.languageForCvDao.save(languageForCv);
		return new SuccessResult("Language added");
	}

	@Override
	public Result update(LanguageForCv languageForCv) {
		this.languageForCvDao.save(languageForCv);
		return new SuccessResult("Language updated");
	}

	@Override
	public Result delete(int id) {
		this.languageForCvDao.deleteById(id);
		return new SuccessResult("Language deleted");
	}

	@Override
	public DataResult<LanguageForCv> getById(int id) {
		return new SuccessDataResult<LanguageForCv>(this.languageForCvDao.getById(id));
	}

	@Override
	public DataResult<List<LanguageForCv>> getAll() {
		return new SuccessDataResult<List<LanguageForCv>>(this.languageForCvDao.findAll());
	}

	@Override
	public DataResult<List<LanguageForCv>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<LanguageForCv>>(this.languageForCvDao.getAllByJobSeeker_id(id));
	}

}
