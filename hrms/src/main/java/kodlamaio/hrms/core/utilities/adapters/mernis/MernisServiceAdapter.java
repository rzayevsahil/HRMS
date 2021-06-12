package kodlamaio.hrms.core.utilities.adapters.mernis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.mernisService.FakeMernisService;

@Service
public class MernisServiceAdapter implements MernisService {
	
	@Autowired
	FakeMernisService client= new FakeMernisService();
	
	@Override
	public boolean checkIfRealPerson(String nationalityId, String firstName, String lastName,
			int dateOfBirth) {		
		
		boolean result = client.TCKimlikDogrula(nationalityId, firstName, lastName, dateOfBirth);
		
		return result;
	}

	



}
