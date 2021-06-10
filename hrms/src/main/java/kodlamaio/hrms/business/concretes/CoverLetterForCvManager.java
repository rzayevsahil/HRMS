package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CoverLetterForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CoverLetterForCvDao;
import kodlamaio.hrms.entities.concretes.CoverLetterForCv;

@Service
public class CoverLetterForCvManager implements CoverLetterForCvService{

	private CoverLetterForCvDao coverLetterForCvDao;
	
	@Autowired
	public CoverLetterForCvManager(CoverLetterForCvDao coverLetterForCvDao) {
		super();
		this.coverLetterForCvDao = coverLetterForCvDao;
	}

		
	@Override
	public Result add(CoverLetterForCv coverLetterForCv) {
		this.coverLetterForCvDao.save(coverLetterForCv);
		return new SuccessResult("CoverLetterForCv added");
	}

	@Override
	public Result update(CoverLetterForCv coverLetterForCv) {
		this.coverLetterForCvDao.save(coverLetterForCv);
		return new SuccessResult("CoverLetterForCv updated");
	}

	@Override
	public Result delete(int id) {
		this.coverLetterForCvDao.deleteById(id);
		return new SuccessResult("CoverLetterForCv deleted");
	}

	@Override
	public DataResult<CoverLetterForCv> getById(int id) {
		return new SuccessDataResult<CoverLetterForCv>(this.coverLetterForCvDao.getById(id));
	}

	@Override
	public DataResult<List<CoverLetterForCv>> getAll() {
		return new SuccessDataResult<List<CoverLetterForCv>>(this.coverLetterForCvDao.findAll());
	}

}
