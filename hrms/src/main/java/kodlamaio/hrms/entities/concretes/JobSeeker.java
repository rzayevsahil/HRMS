package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "jobseekers")
public class JobSeeker extends User {

	@Column(name = "first_name")
	@NotNull(message = "Firstname cannot be null")
	@NotBlank
	@Size(min = 2, max = 25, message = "FirstName must be between 2 and 25 characters")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "LastName cannot be null")
	@NotBlank
	@Size(min = 2, max = 25, message = "LastName must be between 2 and 25 characters")
	private String lastName;

	@Column(name = "national_id",unique = true)
	@NotNull(message = "NationalId cannot be null")
	@Size(min = 11, max = 11, message = "NationalId must be 11 characters")
	private String nationalId;

	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker")	
	private List<LanguageForCv> languages;
	
	@JsonIgnore
	@OneToOne(mappedBy = "jobSeeker", optional=false, fetch=FetchType.LAZY)
	private ImageForCv image;
	
	@JsonIgnore
	@OneToMany(mappedBy="jobSeeker")	
	private List<EducationForCv> educations;
	
	@JsonIgnore
	@OneToMany(mappedBy="jobSeeker")	
	private List<ExperienceForCv> experiences;
	
	@JsonIgnore
	@OneToMany(mappedBy="jobSeeker")	
	private List<SkillForCv> skills; 
	
	@JsonIgnore
	@OneToMany(mappedBy="jobSeeker")
	private List<CoverLetterForCv> coverLetters;
	
	@Column(name = "date_of_birth")
	@NotNull(message="DateOfBirth can not be null")
	@Past
	private LocalDate dateOfBirthDate;

	@Column(name = "is_verified", columnDefinition = "boolean default false")
	private boolean isVerified = false;
}
