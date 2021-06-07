package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobadvertisements/")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvert>>  getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody JobAdvert jobAdvertisement){
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("getByIsActiveTrueOrderByApplicationDeadlineAsc")
	public DataResult<List<JobAdvert>>  getByIsActiveTrueOrderByApplicationDeadlineAsc(){
		return this.jobAdvertisementService.getByIsActiveTrueOrderByApplicationDeadlineAsc();
	}	

	@GetMapping("/getByisActiveTrueAndEmployerId")
	public ResponseEntity<?> getByisActiveTrueAndEmployerId(int id){
		return ResponseEntity.ok(this.jobAdvertisementService.getByisActiveTrueAndEmployerId(id));
	}

	@GetMapping("findAllByIsActiveTrue")
	public DataResult<List<JobAdvert>> findAllByIsActiveTrue(){
		return this.jobAdvertisementService.findAllByIsActiveTrue();
	}

}
