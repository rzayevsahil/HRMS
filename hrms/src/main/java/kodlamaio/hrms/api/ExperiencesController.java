package kodlamaio.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ExperienceForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.ExperienceForCv;

@RestController
@RequestMapping("/api/experiences/")
public class ExperiencesController {

	private ExperienceForCvService experienceForCvService;

	@Autowired
	public ExperiencesController(ExperienceForCvService experienceForCvService) {
		super();
		this.experienceForCvService = experienceForCvService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody ExperienceForCv experienceForCv) {
		return ResponseEntity.ok(this.experienceForCvService.add(experienceForCv));
	}
	
	
	
	@PutMapping("update")
	public ResponseEntity<?> update( @Valid @RequestBody ExperienceForCv experienceForCv) {
		return ResponseEntity.ok(this.experienceForCvService.update(experienceForCv));
	}
	
	 @DeleteMapping("delete")
	 public ResponseEntity<?> delete(@RequestParam("id") int id) {
		 return ResponseEntity.ok(this.experienceForCvService.delete(id));
	 }
	 @GetMapping("getbyid")
	 public DataResult<ExperienceForCv> getById(@RequestParam int id){
		 return this.experienceForCvService.getById(id);
	 }
	 @GetMapping("getAllByJobSeekerIdOrderByLeaveAtDesc")
	 public DataResult<List<ExperienceForCv>> getAllByJobSeekerIdOrderByLeaveArDesc(@RequestParam("id") int id){
		 return this.experienceForCvService.getAllByJobSeekerIdOrderByLeaveDateDesc(id);
	 }
	 
	 @GetMapping("getAllJobSeekerId")
	 public DataResult<List<ExperienceForCv>> getAllByJobSeekerId(@RequestParam int id){
		 return this.experienceForCvService.getAllByJobSeekerId(id);
	 }
	 
}
