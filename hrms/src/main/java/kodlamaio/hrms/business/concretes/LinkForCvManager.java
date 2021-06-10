package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LinkForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LinkForCvDao;
import kodlamaio.hrms.entities.concretes.LinkForCv;

@Service
public class LinkForCvManager implements LinkForCvService {

	private LinkForCvDao linkForCvDao;
	
	@Autowired
	public LinkForCvManager(LinkForCvDao linkForCvDao) {
		super();
		this.linkForCvDao = linkForCvDao;
	}
	
	@Override
	public Result add(LinkForCv linkForCv) {
		this.linkForCvDao.save(linkForCv);
		return new SuccessResult("Link added");
	}

	@Override
	public Result update(LinkForCv linkForCv) {
		this.linkForCvDao.save(linkForCv);		
		return new SuccessResult("Link updated");
	}

	@Override
	public Result delete(int id) {
		this.linkForCvDao.deleteById(id);
		return new SuccessResult("\"Link deleted");
	}

	@Override
	public DataResult<List<LinkForCv>> getAll() {
		return new SuccessDataResult<List<LinkForCv>>(this.linkForCvDao.findAll());
	}

	@Override
	public DataResult<LinkForCv> getById(int id) {
		return new SuccessDataResult<LinkForCv>(this.linkForCvDao.getById(id));
	}

	@Override
	public DataResult<List<LinkForCv>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<List<LinkForCv>>(this.linkForCvDao.getAllByJobSeeker_id(id));
	}

}
