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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers/")
@CrossOrigin
public class EmployersController {
 
	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	
	@GetMapping("getAll")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
	}
	
	@PostMapping("Add")
	public ResponseEntity<?> add(@Valid@RequestBody Employer employer) {
		return ResponseEntity.ok(this.employerService.add(employer));
	}
	
	@PutMapping("Update")
	public ResponseEntity<?> update(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(this.employerService.update(employer));
	}
	
	@DeleteMapping("Delete")
	public ResponseEntity<?> delete(@Valid @RequestParam int employerId) {
		return ResponseEntity.ok(this.employerService.delete(employerId));
	}
	
	@GetMapping("getbyid")
	public DataResult<Employer> getById(@RequestParam int id){
		return this.employerService.getById(id);
	}
	
	@GetMapping("getallbyverify")
	public DataResult<List<Employer>> getAllByVerify(){
	return this.employerService.getAllByVerify(); 
	}
	
	@PostMapping("changeverifiedstatus")
	public  ResponseEntity<?> changeIsVerifiedByEmployee(@RequestParam int id) {
		return ResponseEntity.ok(this.employerService.changeIsVerifiedByEmployee(id));
	}
	
	@GetMapping("countById")
	public long  countById(@RequestParam int id) {
		return this.employerService.countById(id);
	}
	
	@GetMapping("countGetAll")
	public long  countGetALL() {
		return this.employerService.countGetAll();
	}
	
}
