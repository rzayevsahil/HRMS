package kodlamaio.hrms.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.City;

public interface CityService {

	DataResult<List<City>> getAll();
	DataResult<City> getById(int id);
	
	Result add(City city) throws FileNotFoundException, DocumentException ;
	Result update(City city);
	Result delete(int cityId);
	
	long countGetAll();
}
