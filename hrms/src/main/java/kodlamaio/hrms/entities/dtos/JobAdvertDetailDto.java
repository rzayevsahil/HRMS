package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertDetailDto {

	private int jobPositionId;	
	private int employerId;	
	private String description;	
	private int cityId;	
	private int minSalary;	
	private int maxSalary;	
	private int openPositionCount;	
	private LocalDate deadLine;
	private boolean isActive;	
	private int workTypeId;	
	private int workHourId;
}
