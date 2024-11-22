package modules.statisticsservice.com.piggymetrics.statistics.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Account;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Currency;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Item;
import modules.statisticsservice.com.piggymetrics.statistics.domain.Saving;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.DataPoint;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.DataPointId;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.ItemMetric;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.StatisticMetric;
import modules.statisticsservice.com.piggymetrics.statistics.repository.DataPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.google.common.collect.ImmutableMap;
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataPointRepository repository;

    @Autowired
    private ExchangeRatesService ratesService;

    /**
     * {@inheritDoc }
     */
    @Override
    public List<DataPoint> findByAccountName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByIdAccount(accountName);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DataPoint save(String accountName, Account account) {
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        DataPointId pointId = new DataPointId(accountName, Date.from(instant));
        Set<ItemMetric> incomes = account.getIncomes().stream().map(this::createItemMetric).collect(Collectors.toSet());
        Set<ItemMetric> expenses = account.getExpenses().stream().map(this::createItemMetric).collect(Collectors.toSet());
        Map<StatisticMetric, BigDecimal> statistics = createStatisticMetrics(incomes, expenses, account.getSaving());
        DataPoint dataPoint = new DataPoint();
        dataPoint.setId(pointId);
        dataPoint.setIncomes(incomes);
        dataPoint.setExpenses(expenses);
        dataPoint.setStatistics(statistics);
        dataPoint.setRates(ratesService.getCurrentRates());
        log.debug("new datapoint has been created: {}", pointId);
        return repository.save(dataPoint);
    }

    private Map<StatisticMetric, BigDecimal> createStatisticMetrics(Set<ItemMetric> incomes, Set<ItemMetric> expenses, Saving saving) {
        BigDecimal savingAmount = ratesService.convert(saving.getCurrency(), Currency.getBase(), saving.getAmount());
        BigDecimal expensesAmount = expenses.stream().map(ItemMetric::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal incomesAmount = incomes.stream().map(ItemMetric::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return ImmutableMap.of(StatisticMetric.EXPENSES_AMOUNT, expensesAmount, StatisticMetric.INCOMES_AMOUNT, incomesAmount, StatisticMetric.SAVING_AMOUNT, savingAmount);
    }

    /**
     * Normalizes given item amount to {@link Currency#getBase()} currency with
     * {@link TimePeriod#getBase()} time period
     */
    private ItemMetric createItemMetric(Item item) {
        BigDecimal amount = ratesService.convert(item.getCurrency(), Currency.getBase(), item.getAmount()).divide(item.getPeriod().getBaseRatio(), 4, RoundingMode.HALF_UP);
        return new ItemMetric(item.getTitle(), amount);
    }
}