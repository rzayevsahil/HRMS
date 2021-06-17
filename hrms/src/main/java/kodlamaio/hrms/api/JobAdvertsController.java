package kodlamaio.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDetailDto;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

@RestController
@RequestMapping("/api/jobadverts/")
@CrossOrigin
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
	
	@PostMapping("Add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertDetailDto jobAdvertDetailDto){
		return ResponseEntity.ok(this.jobAdvertService.add(jobAdvertDetailDto));
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

	@GetMapping("getByisActiveTrueAndEmployerId")
	public ResponseEntity<?> getByisActiveTrueAndEmployerId(int id){
		return ResponseEntity.ok(this.jobAdvertService.getByisActiveTrueAndEmployerId(id));
	}

	@GetMapping("findAllByIsActiveTrue")
	public DataResult<List<JobAdvert>> findAllByIsActiveTrue(){
		return this.jobAdvertService.findAllByIsActiveTrue();
	}
	
	@GetMapping("getJobAdvertDetails")
	public DataResult<List<JobAdvertDto>> getJobAdvertDetails(){
		return this.jobAdvertService.getJobAdvertDetails();
	}
	
	
	
	//-----------------------------------------------------------------------------
	
	
	
	@GetMapping("getbyid")
	public DataResult<JobAdvert> getById(@PathVariable("id") int id){
		return this.jobAdvertService.getById(id);
	}
	
	@PostMapping("changeactivestatus")
	public Result changeIsActiveByEmployee(@RequestParam int id) {
		return this.jobAdvertService.changeIsActiveByEmployee(id);
	}

	@PostMapping("changeopenstatus")
	public Result changeIsOpenByEmployer(@RequestParam int id) {
		return this.jobAdvertService.changeIsOpenByEmployer(id);
	}
	
	@GetMapping("getAllActiveAndOpenJobAdverts")
	public DataResult<List<JobAdvert>> getAllByIsActiveByEmployee(){
		return this.jobAdvertService.getAllByIsActiveByEmployee();
	}
	
	@GetMapping("getAllOpenJobAdvertsAndIsActiveFalse")
	public DataResult<List<JobAdvert>> getAllByIsActiveByEmployee_False(){
		return this.jobAdvertService.getAllByIsActiveByEmployee_False();
	}
	
	@GetMapping("getAllByEmployerId")
	public DataResult<List<JobAdvert>> getAllByEmployerId(@RequestParam int id){
		return this.jobAdvertService.getAllByEmployerId(id);
	}
	
}
