package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CoverLetterForCv;

public interface CoverLetterForCvDao extends JpaRepository<CoverLetterForCv, Integer>{
	
	CoverLetterForCv getById(int id);
}
