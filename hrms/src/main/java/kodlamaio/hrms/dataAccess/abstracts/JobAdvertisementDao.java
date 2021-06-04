package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

    List<JobAdvertisement> getByIsActiveTrueOrderByApplicationDeadlineAsc();

    List<JobAdvertisement> findAllByIsActiveTrue();

    List<JobAdvertisement> getByisActiveTrueAndEmployer_Id(int id);
}
