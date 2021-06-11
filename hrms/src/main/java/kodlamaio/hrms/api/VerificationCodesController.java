package kodlamaio.hrms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;

@RestController
@RequestMapping("/api/verificationcodes/")
public class VerificationCodesController {

	
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public VerificationCodesController (VerificationCodeService verificationCodeService) {
		this.verificationCodeService=verificationCodeService;
	}
	
	
}
