package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvert;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvert, Integer> {

   /* List<JobAdvert> getByIsActiveTrueOrderByApplicationDeadlineAsc();

    List<JobAdvert> findAllByIsActiveTrue();

    List<JobAdvert> getByisActiveTrueAndEmployer_Id(int id);*/
}
