package kodlamaio.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;

@RestController
@RequestMapping("/api/users/")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@GetMapping("getall")
	@Cacheable("allUsers")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping(value="add")
	public ResponseEntity<?>add(@Valid @RequestBody User user){
		 return ResponseEntity.ok(this.userService.add(user));
	}
	
	@PutMapping("update")
	public Result update(@RequestBody User user){
		return this.userService.update(user);
	}
	
	@DeleteMapping("delete")
	public Result delete(@PathVariable("id") int id){
		return this.userService.delete(id);
	}
	
	@GetMapping("getbyid")
	public DataResult<User> getById(@PathVariable("id") int id){
		return this.userService.getById(id);
	}
	
}
