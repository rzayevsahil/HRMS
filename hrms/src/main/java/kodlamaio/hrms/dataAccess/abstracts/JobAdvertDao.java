package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvert;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

    List<JobAdvert> getByIsActiveTrueOrderByDeadlineAsc();

    List<JobAdvert> findAllByIsActiveTrue();

    List<JobAdvert> getByIsActiveTrueAndEmployer_Id(int id);
}
