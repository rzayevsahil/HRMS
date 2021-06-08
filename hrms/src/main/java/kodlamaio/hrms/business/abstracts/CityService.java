package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.City;

public interface CityService {

	DataResult<List<City>> getAll();
	
	Result add(City city);
	Result update(City city);
	Result delete(int cityId);
}
