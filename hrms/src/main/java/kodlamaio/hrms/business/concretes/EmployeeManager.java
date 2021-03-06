package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService{
	
	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public DataResult<Employee> getById(int id) {
		return new SuccessDataResult<Employee>(this.employeeDao.getById(id),"Data listelendi");
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(Employee employee) {
		this.employeeDao.save(employee);
	    return new SuccessResult("Employee added.");
	}

	@Override
	public Result update(Employee employee) {
		this.employeeDao.save(employee);
	    return new SuccessResult("Employee updated.");
	}

	@Override
	public Result delete(int id) {
		this.employeeDao.deleteById(id);
	    return new SuccessResult("Employee deleted.");
	}

	@Override
	public long countGetAll() {
		return this.employeeDao.count();
	}

}
