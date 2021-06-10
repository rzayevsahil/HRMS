package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LinkForCv;

public interface LinkForCvDao extends JpaRepository<LinkForCv, Integer>{

	LinkForCv getById(int id);	
	List<LinkForCv> getAllByJobSeeker_id(int id);
}
