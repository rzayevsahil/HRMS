package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.City;


public interface CityDao extends JpaRepository<City, Integer> {

	boolean existsById(int id);
	List<City> findAllByName(String cityName);
	boolean getById(int id);
	
	City GetById(int id);
	City findCityByName(String name);
}
