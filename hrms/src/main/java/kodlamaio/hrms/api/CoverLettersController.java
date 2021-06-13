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

import kodlamaio.hrms.business.abstracts.CoverLetterForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.CoverLetterForCv;

@RestController
@RequestMapping("/api/coverletters/")
@CrossOrigin
public class CoverLettersController {

	private CoverLetterForCvService coverLettersForCvService;

	@Autowired
	public CoverLettersController(CoverLetterForCvService coverLettersForCvService) {
		super();
		this.coverLettersForCvService = coverLettersForCvService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody CoverLetterForCv coverLetterForCv) {
		return ResponseEntity.ok(this.coverLettersForCvService.add(coverLetterForCv));
	}
	
	
	@PutMapping("update")
	public  ResponseEntity<?> update(@Valid @RequestBody CoverLetterForCv coverLetterForCv) {
		return ResponseEntity.ok(this.coverLettersForCvService.update(coverLetterForCv));
		
	}
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam("id") int id) {
		return ResponseEntity.ok(this.coverLettersForCvService.delete(id));
		
	}
	
	@GetMapping("getById")
	public DataResult<CoverLetterForCv> getById(@RequestParam("id") int id){
		return this.coverLettersForCvService.getById(id);
	}
	
	@GetMapping("getall")
	public DataResult<List<CoverLetterForCv>> getAll(){
		return this.coverLettersForCvService.getAll();
	}
}
