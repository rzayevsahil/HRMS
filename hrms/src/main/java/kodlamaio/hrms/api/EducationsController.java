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

import kodlamaio.hrms.business.abstracts.EducationForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.EducationForCv;

@RestController
@RequestMapping("/api/educations/")
@CrossOrigin
public class EducationsController {
		
	private EducationForCvService educationForCvService;
	@Autowired
	public EducationsController(EducationForCvService educationForCvService) {
		super();
		this.educationForCvService = educationForCvService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody EducationForCv educaitonForCv) {
		return ResponseEntity.ok(this.educationForCvService.add(educaitonForCv));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody EducationForCv educationForCv) {
		return ResponseEntity.ok(this.educationForCvService.update(educationForCv));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam("id") int id) {
		return ResponseEntity.ok(this.educationForCvService.delete(id));
	}
	
	@GetMapping("getbyid")
	public DataResult<EducationForCv> getById(@RequestParam int id){
		return this.educationForCvService.getById(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<EducationForCv>> getAll() {
		return this.educationForCvService.getAll();
	}
	
	@GetMapping("getAllByJobSeekerIdOrderByGraduationAtDesc")
	public DataResult<List<EducationForCv>> getAllByJobSeekerIdOrderByGraduationAtDesc (@RequestParam int id){
		return this.educationForCvService.getByJobSeekerIdOrderByGraduationYearDesc(id);
	}
	@GetMapping("getAllByJobSeekerId")
	public DataResult<List<EducationForCv>> getAllByJobSeekerId(@RequestParam int id){
		return this.educationForCvService.getAllByJobSeekerId(id);
	}
}
