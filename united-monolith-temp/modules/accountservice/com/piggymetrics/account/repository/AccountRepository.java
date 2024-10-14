package modules.accountservice.com.piggymetrics.account.repository;
import modules.accountservice.com.piggymetrics.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByName(String name);
}