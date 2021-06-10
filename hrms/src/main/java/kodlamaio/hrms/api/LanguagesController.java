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

import kodlamaio.hrms.business.abstracts.LanguageForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.LanguageForCv;

@RestController
@RequestMapping("api/languages/")
public class LanguagesController {

	private LanguageForCvService languageForCvService;

	@Autowired	
	public LanguagesController(LanguageForCvService laguageForCvService) {
		super();
		this.languageForCvService = laguageForCvService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add (@Valid @RequestBody LanguageForCv languageForCv) {
		return ResponseEntity.ok(this.languageForCvService.add(languageForCv));
	}
	
	
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody LanguageForCv languageForCv) {
		return ResponseEntity.ok(this.languageForCvService.update(languageForCv));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete (@RequestParam("id") int id) {
		return ResponseEntity.ok(this.languageForCvService.delete(id));
	}
	
	@GetMapping("getbyid")
	public DataResult<LanguageForCv> getById(@RequestParam("id") int id ) {
		return this.languageForCvService.getById(id);
	}
	@GetMapping("getall")
	public DataResult<List<LanguageForCv>> getAll(){
		return this.languageForCvService.getAll();
	}
	
	@GetMapping("getAllByJobSeekerId")
	public DataResult<List<LanguageForCv>> getAllByJobSeekerId(@RequestParam int id){
		return this.languageForCvService.getAllByJobSeekerId(id);
				
	}
}
