package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.Favorites;

public interface FavoritesService{

	DataResult<List<Favorites>> getAll();
	DataResult<List<Favorites>> getByJobSeekerId(int id);
	DataResult<List<Favorites>> getByJobAdvertId(int id);
	DataResult<Favorites> getById(int id);
	
	Result add(Favorites favorites);
	Result delete(int jobSeekerId,int jobAdvertId);
	
	long countGetAll();
	
	DataResult<Favorites> getByJobSeekerIdAndJobAdvertId(int jobSeekerId,int jobAdvertId);
	
}
