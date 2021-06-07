package kodlamaio.hrms.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement"})
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper = false)
public class Employer extends User {

	@Column(name="company_name")
	@NotNull(message="CompanyName can not be null")
	@NotBlank
	private String companyName;
	
	@Column(name="website")
	@NotNull(message="Website cannot be null")
	@NotBlank
	private String website;
	
	@Column(name="phone_number")
	@NotNull(message="PhoneNumber cannot be null")
	@NotBlank
	private String phoneNumber;	
	
	@Column(name = "is_verified", columnDefinition = "boolean default false")
	private boolean isVerified = false;	 
	
	@JsonIgnore
	@OneToMany(mappedBy = "employer")
    private List<JobAdvert> jobAdvertisement;
	
}
