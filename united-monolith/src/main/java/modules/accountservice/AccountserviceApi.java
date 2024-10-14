package modules.accountservice;
import modules.accountservice.com.piggymetrics.account.controller.AccountController;
import modules.accountservice.com.piggymetrics.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AccountserviceApi {
    @Autowired
    AccountController accountController;

    public Account getAccountsPathvariable(String name) {
        return accountController.getAccountByNameLocal(name);
    }
}