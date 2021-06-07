package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cv_images")
public class ImageForCv extends Base{

	 @Column(name = "url")
	 private String url;

	 @Column(name = "uploaded_at" , columnDefinition = "Date default CURRENT_DATE")
	 private LocalDate uploadedAt;

	//@JsonIgnore()
	 @OneToOne(optional=false,fetch=FetchType.LAZY)	 
	 @JoinColumn(name = "jobseeker_id",referencedColumnName="user_id")
	 private JobSeeker jobSeeker;
	
}
