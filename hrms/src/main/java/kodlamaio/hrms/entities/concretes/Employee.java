package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="employees")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertConfirmations"})
public class Employee extends User{
	

	@Column(name = "first_name")
	@NotNull(message="firstname cannot be null")
	@NotBlank
	@Size(min=2,max=25,message="FirstName must be between 2 and 25 characters")
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull(message="LastName cannot be null")
	@NotBlank
	@Size(min=2,max=25,message="FirstName must be between 2 and 25 characters")
	private String lastName;
	
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private List<JobAdvertConfirmation> jobAdvertConfirmations;
	
	
}
