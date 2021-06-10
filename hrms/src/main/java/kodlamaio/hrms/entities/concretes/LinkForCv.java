package kodlamaio.hrms.entities.concretes;

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
@Table(name="cv_links")
public class LinkForCv extends Base{

	@Column(name = "name")
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@Column(name = "url")
	@NotNull(message = "Url can not be null")
	private String url;	
	
	@ManyToOne
	@JoinColumn(name = "jobseeker_id")
	private JobSeeker jobSeeker;
}
