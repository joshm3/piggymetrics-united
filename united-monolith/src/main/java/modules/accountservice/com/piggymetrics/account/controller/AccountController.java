package modules.accountservice.com.piggymetrics.account.controller;
import java.security.Principal;
import modules.accountservice.com.piggymetrics.account.domain.Account;
import modules.accountservice.com.piggymetrics.account.domain.User;
import modules.accountservice.com.piggymetrics.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    public Account getAccountByNameLocal(@PathVariable
    String name) {
        return accountService.findByName(name);
    }

    @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public Account getAccountByName(@PathVariable
    String name) {
        return accountService.findByName(name);
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public Account getCurrentAccount(Principal principal) {
        return accountService.findByName(principal.getName());
    }

    @RequestMapping(path = "/current", method = RequestMethod.PUT)
    public void saveCurrentAccount(Principal principal, @Valid
    @RequestBody
    Account account) {
        accountService.saveChanges(principal.getName(), account);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Account createNewAccount(@Valid
    @RequestBody
    User user) {
        return accountService.create(user);
    }
}