package kodlamaio.hrms.api;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.itextpdf.text.DocumentException;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.City;

@RestController
@RequestMapping("/api/cities/")
@CrossOrigin
public class CitiesController { 

	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("getall")
	@Cacheable("allCities")
	public DataResult<List<City>> getAll(){
		return this.cityService.getAll();
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody City city)throws FileNotFoundException, DocumentException {
		return ResponseEntity.ok(this.cityService.add(city));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody City city) {
		return ResponseEntity.ok(this.cityService.update(city));
	}
		
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@Valid @RequestParam int cityId) {
		return ResponseEntity.ok(this.cityService.delete(cityId));
	}
	
	@GetMapping("getbyid")
	public DataResult<City> getById(@RequestParam int id){
		return this.cityService.getById(id);
	}
	
	@GetMapping("countGetAll")
	public long  countGetALL() {
		return this.cityService.countGetAll();
	}
	
}
