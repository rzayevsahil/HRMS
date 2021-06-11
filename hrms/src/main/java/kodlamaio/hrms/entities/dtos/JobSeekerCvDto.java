package kodlamaio.hrms.entities.dtos;

import java.util.List;

import kodlamaio.hrms.entities.concretes.EducationForCv;
import kodlamaio.hrms.entities.concretes.ExperienceForCv;
import kodlamaio.hrms.entities.concretes.ImageForCv;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.LanguageForCv;
import kodlamaio.hrms.entities.concretes.LinkForCv;
import kodlamaio.hrms.entities.concretes.SkillForCv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerCvDto {
	
	public JobSeeker jobSeeker;
	public List<EducationForCv> educations;
	public List<SkillForCv> skills;
	public List<LinkForCv> links;
	public List<LanguageForCv> languages;
	public List<ExperienceForCv> experiences;
	public ImageForCv image;
}