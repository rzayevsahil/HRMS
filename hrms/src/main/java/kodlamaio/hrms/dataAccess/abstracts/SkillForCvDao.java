package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.SkillForCv;

public interface SkillForCvDao extends JpaRepository<SkillForCv, Integer>{

	SkillForCv getById(int id);
	List<SkillForCv> getAllByJobSeeker_id(int id);
}
