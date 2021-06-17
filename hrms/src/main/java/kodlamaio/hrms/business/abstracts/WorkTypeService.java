package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.WorkType;

public interface WorkTypeService {

	Result add(WorkType workType);
	
	DataResult<List<WorkType>> getAll();
}
