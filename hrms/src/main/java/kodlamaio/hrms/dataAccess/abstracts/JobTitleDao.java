package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobTitleDao extends JpaRepository<JobPosition, Integer> {
	
	/*List<JobPosition> findAllByTitle(String title);*/
}
