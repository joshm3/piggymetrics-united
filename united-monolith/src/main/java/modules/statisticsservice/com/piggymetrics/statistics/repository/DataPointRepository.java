package modules.statisticsservice.com.piggymetrics.statistics.repository;
import java.util.List;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.DataPoint;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.DataPointId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, DataPointId> {
    List<DataPoint> findByIdAccount(String account);
}