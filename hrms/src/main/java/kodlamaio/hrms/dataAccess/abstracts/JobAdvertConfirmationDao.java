package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertConfirmation;

public interface JobAdvertConfirmationDao extends JpaRepository<JobAdvertConfirmation, Integer>{

}
