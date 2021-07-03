package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.VerificationEmployer;

public interface VerificationEmployerDao extends JpaRepository<VerificationEmployer, Integer> {
	VerificationEmployer getById(int userId);

	@Query("From VerificationEmployer where is_verified=false")
	List<VerificationEmployer> getAllByVerifyFalse();

}
