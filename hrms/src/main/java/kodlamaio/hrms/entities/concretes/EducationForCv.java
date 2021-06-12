package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cv_schools")
public class EducationForCv extends Base{

	 @Column(name = "school_name")
	 @NotNull(message="SchoolName can not be null")
	 private String schoolName;

	 @Column(name = "department_name")
	 @NotNull(message="DepartmentName can not be null")
	 private String departmentName;

	 @Column(name = "start_year")
	 @NotNull(message="StartYear can not be null")
	 private LocalDate startYear;

	 @Column(name = "graduation_year",nullable=true)
	 private LocalDate graduationYear;

	//@JsonIgnore()
	 @ManyToOne()	 
	 @JoinColumn(name = "jobseeker_id")
	 private JobSeeker jobSeeker;
	
}
