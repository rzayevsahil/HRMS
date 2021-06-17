package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkHourService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkHourDao;
import kodlamaio.hrms.entities.concretes.WorkHour;

@Service
public class WorkHourManager implements WorkHourService {
	
	private WorkHourDao workHourDao;	
	
	@Autowired
	public WorkHourManager(WorkHourDao workHourDao) {
		super();
		this.workHourDao = workHourDao;
	}

	@Override
	public Result add(WorkHour workHour) {
		this.workHourDao.save(workHour);
		return new SuccessResult("job Seeker Added");
	}

	@Override
	public DataResult<List<WorkHour>> getAll() {
		return new SuccessDataResult<List<WorkHour>>(this.workHourDao.findAll());
	}

}
