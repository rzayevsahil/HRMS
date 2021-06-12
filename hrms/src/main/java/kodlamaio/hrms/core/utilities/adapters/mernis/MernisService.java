package kodlamaio.hrms.core.utilities.adapters.mernis;


public interface MernisService {

	boolean checkIfRealPerson(String nationalityId, String firstName, String lastName,int dateOfBirth);
}
