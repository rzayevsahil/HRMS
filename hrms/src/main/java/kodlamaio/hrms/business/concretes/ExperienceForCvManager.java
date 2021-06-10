package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ExperienceForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceForCvDao;
import kodlamaio.hrms.entities.concretes.ExperienceForCv;

@Service
public class ExperienceForCvManager implements ExperienceForCvService {

	private ExperienceForCvDao experienceForCvDao;
	
	@Autowired
	public ExperienceForCvManager(ExperienceForCvDao experienceForCvDao) {
		super();
		this.experienceForCvDao = experienceForCvDao;
	}
	
	@Override
	public Result add(ExperienceForCv experienceForCv) {
		this.experienceForCvDao.save(experienceForCv);
		return new SuccessResult("Experience added");
	}

	@Override
	public Result update(ExperienceForCv experienceForCv) {
		this.experienceForCvDao.save(experienceForCv);
		return new SuccessResult("Experience updated");
	}

	@Override
	public Result delete(int id) {
		this.experienceForCvDao.deleteById(id);
		return new SuccessResult("Experience deleted");
	}

	@Override
	public DataResult<ExperienceForCv> getById(int id) {
		return new SuccessDataResult<ExperienceForCv>(this.experienceForCvDao.getById(id));
	}

	@Override
	public DataResult<List<ExperienceForCv>> getAll() {
		return new SuccessDataResult<List<ExperienceForCv>>(this.experienceForCvDao.findAll());
	}

	@Override
	public DataResult<List<ExperienceForCv>> getAllByJobSeekerIdOrderByLeaveDateDesc(int id) {
		return new SuccessDataResult<List<ExperienceForCv>>(this.experienceForCvDao.getAllByJobSeeker_idOrderByLeaveDateDesc(id));
	}

	@Override
	public DataResult<List<ExperienceForCv>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<ExperienceForCv>>(this.experienceForCvDao.getAllByJobSeeker_id(id));
	}

}
