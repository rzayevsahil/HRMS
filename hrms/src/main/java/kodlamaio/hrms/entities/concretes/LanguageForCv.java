package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Table(name = "cv_foreign_languages")
public class LanguageForCv extends Base {


	@Column(name = "language")
	@NotNull(message="Language can not be null")
	private String language;

	@Column(name = "level")
	@Min(1)
	@Max(5)
	@NotNull(message="Level can not be null")
	private short level;

	//@JsonIgnore()
	@ManyToOne()	 
	@JoinColumn(name = "jobseeker_id")
	private JobSeeker jobSeeker;
}
