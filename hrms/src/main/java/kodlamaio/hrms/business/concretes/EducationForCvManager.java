package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
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
		Result result=BusinessRules.run(startGreatThanGraduationControl(educationForCv));
		if (result.isSuccess()) {
			this.educationForCvDao.save(educationForCv);
			return new SuccessResult("Education added");
		}
		return result;
	}

	@Override
	public Result update(EducationForCv educationForCv) {
		Result result=BusinessRules.run(startGreatThanGraduationControl(educationForCv));
		if (result.isSuccess()) {
			this.educationForCvDao.save(educationForCv);
			return new SuccessResult("Education updated");
		}
		return result;
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
	
	//********************* KURALLAR ***************************
	
	private Result startGreatThanGraduationControl(EducationForCv educationForCv) {
		if (educationForCv.getGraduationYear()==null) {
			return new SuccessResult();
		} else if (educationForCv.getGraduationYear().isBefore(educationForCv.getStartYear())) {
			return new ErrorResult("StartYear can not be large from GraduationYear");
		}
		return new SuccessResult();
	}

}
