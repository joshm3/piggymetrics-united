package modules.notificationservice.com.piggymetrics.notification.repository;
import java.util.List;
import modules.notificationservice.com.piggymetrics.notification.domain.Recipient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
@Repository
public interface RecipientRepository extends CrudRepository<Recipient, String> {
    Recipient findByAccountName(String name);

    @Query("{ $and: [ {'scheduledNotifications.BACKUP.active': true }, { $where: 'this.scheduledNotifications.BACKUP.lastNotified < " + "new Date(new Date().setDate(new Date().getDate() - this.scheduledNotifications.BACKUP.frequency ))' }] }")
    List<Recipient> findReadyForBackup();

    @Query("{ $and: [ {'scheduledNotifications.REMIND.active': true }, { $where: 'this.scheduledNotifications.REMIND.lastNotified < " + "new Date(new Date().setDate(new Date().getDate() - this.scheduledNotifications.REMIND.frequency ))' }] }")
    List<Recipient> findReadyForRemind();
}