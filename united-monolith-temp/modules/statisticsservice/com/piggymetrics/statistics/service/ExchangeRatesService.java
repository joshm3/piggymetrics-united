package modules.statisticsservice.com.piggymetrics.statistics.service;
import java.math.BigDecimal;
import java.util.Map;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Currency;
public interface ExchangeRatesService {
    /**
     * Requests today's foreign exchange rates from a provider
     * or reuses values from the last request (if they are still relevant)
     *
     * @return current date rates
     */
    Map<Currency, BigDecimal> getCurrentRates();

    /**
     * Converts given amount to specified currency
     *
     * @param from
     * 		{@link Currency}
     * @param to
     * 		{@link Currency}
     * @param amount
     * 		to be converted
     * @return converted amount
     */
    BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}