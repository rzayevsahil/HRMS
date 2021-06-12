package kodlamaio.hrms.core.utilities.adapters.mernis;

import java.time.LocalDateTime;

public interface UserCheckService {

	boolean checkIfRealPerson(String nationalityId, String firstName, String lastName,LocalDateTime dateOfBirth);
}
