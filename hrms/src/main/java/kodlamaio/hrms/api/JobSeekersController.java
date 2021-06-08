package kodlamaio.hrms.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorDataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseekers/")
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
	
	@PostMapping(value="update")
	public ResponseEntity<?> update(@Valid @RequestBody JobSeeker jobSeekers) {
		return ResponseEntity.ok(jobseekerService.update(jobSeekers));
	}
	
	@PostMapping(value="delete")
	public ResponseEntity<?> delete(@Valid @RequestParam int id) {
		return ResponseEntity.ok(jobseekerService.delete(id));
	}
	
	@GetMapping("getById")
	public DataResult<JobSeeker> getJobseekerByNationalId(@RequestParam int id){
		return jobseekerService.getById(id);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());			
		}
		
		ErrorDataResult<Object> errors=new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		return errors;
	}
	
}
