package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.hrms.business.abstracts.FavoritesService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.Favorites;

@RestController
@RequestMapping("/api/favorites/")
@CrossOrigin
public class FavoritesController {

	
	private FavoritesService favoriteService;
	
	@Autowired
	public FavoritesController(FavoritesService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}

	@GetMapping("getAll")
	public DataResult<List<Favorites>> getAll(){
		return this.favoriteService.getAll();
	}
	
	@GetMapping("getByJobSeekerId")
	public DataResult<List<Favorites>> getByJobSeekerId(@RequestParam int id){
		return this.favoriteService.getByJobSeekerId(id);
	}
	
	@GetMapping("getByJobAdvertId")
	public DataResult<List<Favorites>> getByJobAdvertId(@RequestParam int id){
		return this.favoriteService.getByJobAdvertId(id);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody Favorites favorite){
		return ResponseEntity.ok(this.favoriteService.add(favorite));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam int jobSeekerId, @RequestParam int jobAdvertId){
		return ResponseEntity.ok(this.favoriteService.delete(jobSeekerId,jobAdvertId));
	}
	
	@GetMapping("getById")
	public DataResult<Favorites> getById(@RequestParam int id){
		return this.favoriteService.getById(id);
	}
}
