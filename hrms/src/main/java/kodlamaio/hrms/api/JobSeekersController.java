package kodlamaio.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerCvDto;

@RestController
@RequestMapping("/api/jobseekers/")
@CrossOrigin
public class JobSeekersController {

	private JobSeekerService jobseekerService;

	@Autowired
	public JobSeekersController(JobSeekerService jobseekerService) {
		super();
		this.jobseekerService = jobseekerService;
	}
	
	
	@GetMapping("getall")
	public DataResult<List<JobSeeker>> getAll(){
		return jobseekerService.getAll();
	}
	
	@PostMapping(value="add")
	public ResponseEntity<?> add(@Valid @RequestBody JobSeeker jobSeekers) {
		return ResponseEntity.ok(jobseekerService.add(jobSeekers));
	}
	
	@PutMapping(value="update")
	public ResponseEntity<?> update(@Valid @RequestBody JobSeeker jobSeekers) {
		return ResponseEntity.ok(jobseekerService.update(jobSeekers));
	}
	
	@DeleteMapping(value="delete")
	public ResponseEntity<?> delete(@Valid @RequestParam int id) {
		return ResponseEntity.ok(jobseekerService.delete(id));
	}
	 
	@GetMapping("getById")
	public DataResult<JobSeeker> getJobseekerByNationalId(@RequestParam int id){
		return jobseekerService.getById(id);
	}
	
	@GetMapping("getJobseekerCVById")
	public DataResult<JobSeekerCvDto> getJobseekerCVById(@RequestParam int id){
		return this.jobseekerService.getJobseekerCVById(id);
	}
	
}
