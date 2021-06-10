package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EducationForCv;

public interface EducationForCvDao extends JpaRepository<EducationForCv, Integer>{

	EducationForCv getById(int id);
	List<EducationForCv> getAllByJobSeeker_idOrderByGraduationYearDesc(int id);
	List<EducationForCv> getAllByJobSeeker_id(int id);
}
