package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertDto {

	private int id;
	private String companyName;
	private String cityName;
	private String jobPosition;
	private int openPositionCount;
	private LocalDateTime publishedAt = LocalDateTime.now();
	private LocalDate deadline;
}
