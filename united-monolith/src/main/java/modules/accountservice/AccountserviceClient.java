package modules.accountservice;
import com.fasterxml.jackson.core.type.TypeReference;
import modules.accountservice.com.piggymetrics.account.domain.Account;
import modules.statisticsservice.StatisticsserviceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AccountserviceClient {
    @Autowired
    StatisticsserviceApi statisticsserviceApi;

    public void putStatisticsPathvariable(String accountName, Account account) {
        String local0;
        if (accountName != null) {
            local0 = accountName;
        } else
            local0 = null;

        modules.statisticsservice.com.piggymetrics.statistics.domain.Account local1;
        if (account != null) {
            modules.statisticsservice.com.piggymetrics.statistics.domain.Account local2 = null;
            try  {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module());
                byte[] bytes = mapper.writeValueAsBytes(account);
                local2 = mapper.readValue(bytes, new TypeReference<modules.statisticsservice.com.piggymetrics.statistics.domain.Account>() {});
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            local1 = local2;
        } else
            local1 = null;

        statisticsserviceApi.putStatisticsPathvariable(local0, local1);
    }
}