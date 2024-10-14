package modules.notificationservice;
import com.fasterxml.jackson.core.type.TypeReference;
import modules.accountservice.AccountserviceApi;
import modules.accountservice.com.piggymetrics.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class NotificationserviceClient {
    @Autowired
    AccountserviceApi accountserviceApi;

    public String getAccountsPathvariable(String accountName) {
        String local0;
        if (accountName != null) {
            local0 = accountName;
        } else
            local0 = null;

        Account input = accountserviceApi.getAccountsPathvariable(local0);
        String local1 = null;
        try  {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
            byte[] bytes = mapper.writeValueAsBytes(input);
            local1 = mapper.readValue(bytes, new TypeReference<String>() {});
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return local1;
    }
}