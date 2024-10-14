package modules.statisticsservice;
import modules.statisticsservice.com.piggymetrics.statistics.controller.StatisticsController;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class StatisticsserviceApi {
    @Autowired
    StatisticsController statisticsController;

    public void putStatisticsPathvariable(String accountName, Account account) {
        statisticsController.saveAccountStatisticsLocal(accountName, account);
    }
}