package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="job_adverts")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","favorites"})
public class JobAdvert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description",length = 3000)
	@Type(type="text")
	@NotNull(message="Description can not be null")
	@NotBlank
	private String description;	

	@Column(name = "salary_min")
	@Min(3000)
	private int salaryMin;

	@Column(name = "salary_max")
	private int salaryMax;
	
	@Column(name = "open_position_count")
	@Min(value=1)
	@NotNull(message="PositionCount can not be null")
	private int openPositionCount;

	@Column(name = "deadline")
	@NotNull(message="PositionCount can not be null")
	@Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message="Deadline can not be null")
	private LocalDate deadline;
	
	@Column(name = "published_at",columnDefinition = "Date default CURRENT_DATE")
	@NotNull(message="PublishedAt can not be null")
	private LocalDate publishedAt= LocalDate.now();

	@Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
	//@JsonIgnore
	private LocalDate createdAt = LocalDate.now();

	
	@Column(name = "is_open", columnDefinition = "boolean default true")
	private boolean isOpen = true;

	
	@Column(name = "is_active", columnDefinition = "boolean default false")
	private boolean isActive = false;

	@JsonIgnore
	@Column(name = "is_deleted", columnDefinition = "boolean default false")
	private boolean isDeleted = false;

	@ManyToOne
	@JoinColumn(name = "job_position_id")
	private JobPosition jobPosition;

	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name="work_hour_id")
	private WorkHour workHour;
	
	@ManyToOne
	@JoinColumn(name="work_type_id")
	private WorkType workType;
	
	@OneToOne(mappedBy="jobAdvert")
	@JsonIgnore
	private JobAdvertConfirmation jobAdvertConfirmation;
	

	@JsonIgnore
	@OneToMany(mappedBy="jobAdvert")	
	private List<Favorites> favorites;
}
