package modules.notificationservice.com.piggymetrics.notification.service;
import java.io.IOException;
import javax.mail.MessagingException;
import modules.notificationservice.com.piggymetrics.notification.domain.NotificationType;
import modules.notificationservice.com.piggymetrics.notification.domain.Recipient;
public interface EmailService {
    void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;
}