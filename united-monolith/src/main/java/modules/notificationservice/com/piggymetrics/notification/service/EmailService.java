package modules.notificationservice.com.piggymetrics.notification.service;
import java.io.IOException;
import modules.notificationservice.com.piggymetrics.notification.domain.NotificationType;
import modules.notificationservice.com.piggymetrics.notification.domain.Recipient;
import javax.mail.MessagingException;
public interface EmailService {
    void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;
}