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

import kodlamaio.hrms.business.abstracts.SkillForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.SkillForCv;

@RestController
@RequestMapping("/api/skills/")
@CrossOrigin
public class SkillsController {
	private SkillForCvService skillForCvService;

	 @Autowired
	public SkillsController(SkillForCvService skillForCvService) {
		super();
		this.skillForCvService = skillForCvService;
	}
	 
	 
	 @PostMapping("add")
		public ResponseEntity<?> add(@Valid @RequestBody SkillForCv skillForCv){
		 return ResponseEntity.ok(this.skillForCvService.add(skillForCv));
		}
	 
	
		@PutMapping("update")
		public ResponseEntity<?> update(@Valid @RequestBody SkillForCv skillForCv){
			return ResponseEntity.ok(this.skillForCvService.update(skillForCv));
		}
		
		@DeleteMapping("delete")
		public ResponseEntity<?> delete(@RequestParam("id") int id){
			return ResponseEntity.ok(this.skillForCvService.delete(id));
		}
		
		@GetMapping("getbyid")
		public DataResult<SkillForCv> getById(@RequestParam("id") int id){
			return this.skillForCvService.getById(id);
		}
		
		@GetMapping("getall")
		public DataResult<List<SkillForCv>> getAll(){
			return this.skillForCvService.getAll();
		}
		
		@GetMapping("/getAllByJobseekerId")
		public DataResult<List<SkillForCv>> getAllByJobseekerId(@RequestParam int id){
			return this.skillForCvService.getAllByJobSeekerId(id);
		}
}
