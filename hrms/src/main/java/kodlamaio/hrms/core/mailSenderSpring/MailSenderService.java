package kodlamaio.hrms.core.mailSenderSpring;



public interface MailSenderService {

	void sendSimpleEmail(String toEmail, String subject, String body); // basit

    //void sendEmailWithAttachment(String toEmail, String subject, String body, String pathToAttachment) throws MessagingException; // ek dosya
}


