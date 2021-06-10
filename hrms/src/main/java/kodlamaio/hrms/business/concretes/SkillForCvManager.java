package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SkillForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SkillForCvDao;
import kodlamaio.hrms.entities.concretes.SkillForCv;

@Service
public class SkillForCvManager implements SkillForCvService {

	private SkillForCvDao skillForCvDao;
	
	public SkillForCvManager(SkillForCvDao skillForCvDao) {
		super();
		this.skillForCvDao = skillForCvDao;
	}
	
	@Override
	public Result add(SkillForCv skillForCv) {
		this.skillForCvDao.save(skillForCv);
		return new SuccessResult("Skill added");
	}

	@Override
	public Result delete(int id) {
		this.skillForCvDao.deleteById(id);
		return new SuccessResult("Skill deleted");
	}

	@Override
	public Result update(SkillForCv skillForCv) {
		this.skillForCvDao.save(skillForCv);
		return new SuccessResult("Skill updated");
	}

	@Override
	public DataResult<SkillForCv> getById(int id) {
		return new SuccessDataResult<SkillForCv>(this.skillForCvDao.getById(id));
	}

	@Override
	public DataResult<List<SkillForCv>> getAll() {
		return new SuccessDataResult<List<SkillForCv>>(this.skillForCvDao.findAll());
	}

	@Override
	public DataResult<List<SkillForCv>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<SkillForCv>>(this.skillForCvDao.getAllByJobSeeker_id(id));
	}

}
