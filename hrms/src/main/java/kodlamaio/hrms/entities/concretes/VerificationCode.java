package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="verification_codes")
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="code")
	private String code;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	@Column(name="created_at", columnDefinition = "Date default CURRENT_DATE")
	private LocalDateTime createAt = LocalDateTime.now(); 

	public VerificationCode(int userId, String code, boolean isConfirmed, LocalDateTime createAt) {
		super();
		this.userId = userId;
		this.code = code;
		this.isConfirmed = isConfirmed;
		this.createAt = createAt;
	}
}
