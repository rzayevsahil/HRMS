package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cv_cover_letters")
@NoArgsConstructor
@AllArgsConstructor
public class CoverLetterForCv extends Base{

	@Column(name = "content")
	private String content;
	
	public CoverLetterForCv(String content, int jobseekerId) {
		super();
		this.content = content;
		this.jobSeeker.setId(jobseekerId);
	}

	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "jobseeker_id")	
	private JobSeeker jobSeeker;
}
