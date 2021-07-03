package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationEmployerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.VerificationEmployer;

@RestController
@RequestMapping("/api/verificationemployer/")
@CrossOrigin
public class VerificationEmployersController {

	private VerificationEmployerService verificationEmployerService;

	@Autowired
	public VerificationEmployersController(VerificationEmployerService verificationEmployerService) {
		super();
		this.verificationEmployerService = verificationEmployerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody VerificationEmployer verificationEmployer) {
		return this.verificationEmployerService.add(verificationEmployer);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestParam int id) {
		return this.verificationEmployerService.delete(id);
	}
	
	@GetMapping("getbyid")
	public DataResult<VerificationEmployer> getbyid(@RequestParam int id){
		return this.verificationEmployerService.getById(id);
	}
	@GetMapping("getall")
	public DataResult<List<VerificationEmployer>> getAll(){
	return this.verificationEmployerService.getAll();	
	}
	
	@GetMapping("getAllByVerifyFalse")
	public DataResult<List<VerificationEmployer>> getAllByVerifyFalse(){
		return this.verificationEmployerService.getAllByVerifyFalse();
	}
}
