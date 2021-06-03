package kodlamaio.hrms.core.utilities.adaptor;

import java.util.regex.Pattern;


public class EmailSendAdaptor implements EmailAdaptor {
	
	@Override
	public boolean emailSend(String email) {
		
		return emailFormatCheck(email);
	}

	
	//email format check
	
	public static final Pattern validEmail = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$",
			Pattern.CASE_INSENSITIVE);
	
	
	private boolean emailFormatCheck(String email) {
		if(!validEmail.matcher(email).find()) {
			System.out.println("Geçerli bir mail adresi girin!");
			return false;
		}
		return true;
	}

}
