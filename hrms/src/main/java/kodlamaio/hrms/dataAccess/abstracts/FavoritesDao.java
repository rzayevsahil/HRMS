package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Favorites;

public interface FavoritesDao extends JpaRepository<Favorites, Integer> {

	List<Favorites> getAllByJobSeeker_Id(int id);
	Favorites getIdByJobSeeker_Id(int id);
	//Favorites getByJobAdvert_Id(int id);
	Favorites getById(int id);
	
	List<Favorites> getByJobSeeker_Id(int jobSeekerId);
	
	List<Favorites> getByJobAdvert_Id(int jobAdvertId);
	
	Favorites getByJobSeekerIdAndJobAdvertId(int jobSeekerId,int jobAdvertId);
}
