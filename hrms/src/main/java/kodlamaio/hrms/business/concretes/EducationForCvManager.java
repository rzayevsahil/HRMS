package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EducationForCvDao;
import kodlamaio.hrms.entities.concretes.EducationForCv;

@Service
public class EducationForCvManager implements EducationForCvService  {

private EducationForCvDao educationForCvDao;
	
	@Autowired
	public EducationForCvManager(EducationForCvDao educationForCvDao) {
		super();
		this.educationForCvDao = educationForCvDao;
	}

	
	@Override
	public Result add(EducationForCv educationForCv) {
		this.educationForCvDao.save(educationForCv);
		return new SuccessResult("Education added");
	}

	@Override
	public Result update(EducationForCv educationForCv) {
		this.educationForCvDao.save(educationForCv);
		return new SuccessResult("Education updated");
	}

	@Override
	public Result delete(int id) {
		this.educationForCvDao.deleteById(id);
		return new SuccessResult("Education deleted");
	}

	@Override
	public DataResult<List<EducationForCv>> getAll() {
		return new SuccessDataResult<List<EducationForCv>>(this.educationForCvDao.findAll());
	}

	@Override
	public DataResult<EducationForCv> getById(int id) {
		return new SuccessDataResult<EducationForCv>(this.educationForCvDao.getById(id));
	}

	@Override
	public DataResult<List<EducationForCv>> getByJobSeekerIdOrderByGraduationYearDesc(int id) {
		return new SuccessDataResult<List<EducationForCv>> (this.educationForCvDao.getAllByJobSeeker_idOrderByGraduationYearDesc(id));
	}

	@Override
	public DataResult<List<EducationForCv>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<EducationForCv>>(this.educationForCvDao.getAllByJobSeeker_id(id));
	}

}
