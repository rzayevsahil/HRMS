package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="verification_employer")
public class VerificationEmployer {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_address")
	private String website;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name = "is_verified", columnDefinition = "boolean default false")
	private boolean isVerified=false;
	
	@Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
	@JsonIgnore
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean isActive=true;
	
	@Column(name = "is_deleted", columnDefinition = "boolean default false")
	private boolean isDeleted=false;
	
}
