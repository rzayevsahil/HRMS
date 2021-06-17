package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LanguageForCv;

public interface LanguageForCvDao extends JpaRepository<LanguageForCv, Integer> {

	LanguageForCv getById(int id);
	List<LanguageForCv> getAllByJobSeeker_id(int id);
	List<LanguageForCv> findAllByLanguage(String language);
	LanguageForCv findUserByLanguage(String language);
}
