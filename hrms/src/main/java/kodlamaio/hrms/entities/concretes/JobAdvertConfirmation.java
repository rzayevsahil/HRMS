package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertConfirmation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	@Column(name="confirmation_Date",columnDefinition="DATE DEFAULT CURRENT_DATE")
	@JsonIgnore
	private LocalDateTime confirmationDate=LocalDateTime.now();
	
	
	@OneToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="job_advert_id")
	private JobAdvert jobAdvert;
	
	@ManyToOne()
	@JoinColumn(name="employee_id")
	private Employee employee;
	
}
