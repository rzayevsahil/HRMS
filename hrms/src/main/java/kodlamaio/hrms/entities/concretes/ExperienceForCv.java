package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
@Table(name = "cv_experiences")
public class ExperienceForCv extends Base{

	@Column(name = "workplace_name")
	 @NotNull(message="Workplace can not be null")
    @NotBlank
    private String workplaceName;

    @Column(name = "position")
    @NotNull(message="Position can not be null")
    @NotBlank
    private String position;

    @Column(name = "start_date")
    @NotNull(message="StartDate can not be null")
    @NotBlank
    private LocalDate startDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

  //@JsonIgnore()
    @ManyToOne()    
    @JoinColumn(name = "jobseeker_id")
    private JobSeeker jobSeeker;
}
