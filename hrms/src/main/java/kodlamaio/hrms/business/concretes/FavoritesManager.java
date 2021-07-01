package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.business.abstracts.FavoritesService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.FavoritesDao;
import kodlamaio.hrms.entities.concretes.Favorites;

@Service
public class FavoritesManager implements FavoritesService {
	
	
	private FavoritesDao favoritesDao;

	@Autowired
	public FavoritesManager(FavoritesDao favoritesDao) {
		super();
		this.favoritesDao = favoritesDao;
	}

	@Override
	public DataResult<List<Favorites>> getAll() {
		return new SuccessDataResult<List<Favorites>>(this.favoritesDao.findAll(),"Favoriler listesi");
	}

	@Override
	public DataResult<List<Favorites>> getByJobSeekerId(int id) {
		return new SuccessDataResult<List<Favorites>>(this.favoritesDao.getAllByJobSeeker_Id(id));
	}
	
	@Override
	public DataResult<List<Favorites>> getByJobAdvertId(int jobAdvertId) {
		return new SuccessDataResult<List<Favorites>>(this.favoritesDao.getByJobAdvert_Id(jobAdvertId));
	}

	@Override
	public Result add(Favorites favorites) {
		this.favoritesDao.save(favorites);
		return new SuccessResult("Eklendi");
	}

	@Override
	public Result delete(int jobSeekerId, int jobAdvertId) {
		//iş ilanına göre getirdiği datanın favori id sini aloyoruz
		
		this.favoritesDao.deleteById(this.favoritesDao.getByJobSeekerIdAndJobAdvertId(jobSeekerId, jobAdvertId).getId());
		//System.out.println();
		return new SuccessResult("Silindi");
	}

	@Override
	public DataResult<Favorites> getById(int id) {
		return new SuccessDataResult<Favorites>(this.favoritesDao.getById(id));
	}

}
