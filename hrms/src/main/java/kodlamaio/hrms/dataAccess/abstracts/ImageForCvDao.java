package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ImageForCv;

public interface ImageForCvDao extends JpaRepository<ImageForCv, Integer> {

	ImageForCv getById(int id);	
	ImageForCv getByJobSeeker_id(int id);
}
