package kodlamaio.hrms.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobadverts/")
public class JobAdvertsController {

	private JobAdvertService jobAdvertService;

	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService) {
		super();
		this.jobAdvertService = jobAdvertService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvert>>  getAll(){
		return this.jobAdvertService.getAll();
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvert jobAdvertisement){
		return ResponseEntity.ok(this.jobAdvertService.add(jobAdvertisement));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody JobAdvert jobAdvertisement){
		return ResponseEntity.ok(this.jobAdvertService.update(jobAdvertisement));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@Valid @RequestParam int id){
		return ResponseEntity.ok(this.jobAdvertService.delete(id));
	}
	
	@GetMapping("getByIsActiveTrueOrderByDeadlineAsc")
	public DataResult<List<JobAdvert>>  getByIsActiveTrueOrderByDeadlineAsc(){
		return this.jobAdvertService.getByIsActiveTrueOrderByDeadlineAsc();
	}	

	@GetMapping("/getByisActiveTrueAndEmployerId")
	public ResponseEntity<?> getByisActiveTrueAndEmployerId(int id){
		return ResponseEntity.ok(this.jobAdvertService.getByisActiveTrueAndEmployerId(id));
	}

	@GetMapping("findAllByIsActiveTrue")
	public DataResult<List<JobAdvert>> findAllByIsActiveTrue(){
		return this.jobAdvertService.findAllByIsActiveTrue();
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
