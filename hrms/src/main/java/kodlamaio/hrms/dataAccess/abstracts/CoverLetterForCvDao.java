package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CoverLetterForCv;

public interface CoverLetterForCvDao extends JpaRepository<CoverLetterForCv, Integer>{
	
	CoverLetterForCv getById(int id);
	
	List<CoverLetterForCv> getAllByJobSeeker_id(int id);
}
