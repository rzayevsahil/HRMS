package kodlamaio.hrms.core.mailSenderSpring;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MailSenderManager implements MailSenderService {

	@Autowired
    private JavaMailSender emailSender;
	

	@Override
	public void sendSimpleEmail(String toEmail, String subject, String body) {

		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
		
		simpleMailMessage.setFrom("hrms@kodlama.io"); //hangi mailden gönderilecek
		simpleMailMessage.setTo(toEmail);  //hangi maile gönderilecek
		simpleMailMessage.setSubject(subject); // mesaj başlığı
		simpleMailMessage.setText(body); //mesaj konusu
		System.out.println("send email");
		emailSender.send(simpleMailMessage);
		
	}


}
