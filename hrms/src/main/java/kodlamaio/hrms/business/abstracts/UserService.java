package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;

public interface UserService {
	
	DataResult<List<User>> getAll();
	
	Result add(User user);	
	Result update(User user);
	Result delete(int id);
	
	DataResult<User> getById(int id);	
	DataResult<User> getUserByEmail(String email);
	
	long countGetAll();
}