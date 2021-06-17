package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}

	@Override
	@CacheEvict(value="allUsers",allEntries = true)
	public Result add(User user) {
		this.userDao.save(user);
	    return new SuccessResult("User added.");
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
	      return new SuccessResult("User updated.");
	}

	@Override
	public Result delete(int id) {
		this.userDao.deleteById(id);
	      return new SuccessResult("User deleted.");
	}
	
	@Override
	public DataResult<User> getUserByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findUserByEmail(email));
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(this.userDao.getOne(id));
	}

}
