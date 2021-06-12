package kodlamaio.hrms.core.utilities.adapters.mernis;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.mernisService.FakeMernisService;

@Service
public class MernisServiceAdapter implements UserCheckService {
	
	@Override
	public boolean checkIfRealPerson(String nationalityId, String firstName, String lastName,
			LocalDateTime dateOfBirth) {

		FakeMernisService client= new FakeMernisService();
		
		boolean result = client.TCKimlikDogrula(nationalityId, firstName, lastName, dateOfBirth);
		

		return result;
	}



}
