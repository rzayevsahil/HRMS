package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	List<Employer> findAllByEmail(String email);
	//boolean getById(int id);	
	Employer getById(int id);
	
	@Query("From Employer where is_verified=false")
	List<Employer> getAllByVerify();
	
	long countById(int id);
}
