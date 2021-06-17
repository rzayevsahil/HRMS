package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.Employee;

public interface EmployeeService {
	
	DataResult<Employee> getById(int id);	
	DataResult<List<Employee>> getAll();
		
	Result add(Employee employee);
	Result update(Employee employee);
	Result delete(int id);
			
}
