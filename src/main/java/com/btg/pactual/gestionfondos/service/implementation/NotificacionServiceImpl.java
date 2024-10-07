package com.btg.pactual.gestionfondos.service.implementation;
import com.btg.pactual.gestionfondos.service.NotificacionService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Slf4j
@Service
@AllArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {


    private final JavaMailSender mailSender;
    private final SnsClient snsClient;

    @Override
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();
        snsClient.publish(request);
    }
}
