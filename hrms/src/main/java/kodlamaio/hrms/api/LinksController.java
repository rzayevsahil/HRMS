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

import kodlamaio.hrms.business.abstracts.LinkForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.LinkForCv;

@RestController
@RequestMapping("/api/links/")
@CrossOrigin
public class LinksController {
	private LinkForCvService linkForCvService;

	@Autowired
	public LinksController(LinkForCvService linkForCvService) {
		super();
		this.linkForCvService = linkForCvService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody LinkForCv linkForCv){
		return ResponseEntity.ok(this.linkForCvService.add(linkForCv));
	}
		
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody LinkForCv linkForCV){
		return ResponseEntity.ok(this.linkForCvService.update(linkForCV));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam("id") int id){
		return ResponseEntity.ok(this.linkForCvService.delete(id));
	}
	
	@GetMapping("getById")
	public DataResult<LinkForCv> getById(@RequestParam("id") int id){
		return this.linkForCvService.getById(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<LinkForCv>> getAll(){
		return this.linkForCvService.getAll();
	}
	
	@GetMapping("getAllByJobseekerId")
	public DataResult<List<LinkForCv>> getAllByJobseekerId(@RequestParam int id){
		return this.linkForCvService.getAllByJobSeekerId(id);
	}
}
