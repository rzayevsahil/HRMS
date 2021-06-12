package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

	List<JobSeeker> findAllByEmail(String email);
	List<JobSeeker> findAllByNationalId(String nationalId);
	JobSeeker findJobseekerByNationalId(String nationalId);
	JobSeeker getById(int id);
	
}
