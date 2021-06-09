package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

    List<JobAdvert> getByIsActiveTrueOrderByDeadlineAsc();

    List<JobAdvert> findAllByIsActiveTrue();

    List<JobAdvert> getByIsActiveTrueAndEmployer_Id(int id); 
    
    @Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertDto(j.id,p.jobPosition,c.name,e.companyName,j.openPositionCount,j.publishedAt,j.deadline) "
    		+ "From JobAdvert j "
    		+ "Inner Join j.employer e "
    		+ "Inner Join j.jobPosition p "
    		+ "Inner Join j.city c")
    List<JobAdvertDto> getJobAdvertDetails();
}
