package modules.statisticsservice.com.piggymetrics.statistics.repository.converter;
import java.util.Date;
import modules.statisticsservice.com.piggymetrics.statistics.domain.timeseries.DataPointId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.mongodb.DBObject;
@Component
public class DataPointIdReaderConverter implements Converter<DBObject, DataPointId> {
    @Override
    public DataPointId convert(DBObject object) {
        Date date = ((Date) (object.get("date")));
        String account = ((String) (object.get("account")));
        return new DataPointId(account, date);
    }
}