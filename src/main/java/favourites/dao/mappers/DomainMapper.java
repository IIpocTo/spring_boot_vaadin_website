package favourites.dao.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class DomainMapper<T> implements RowMapper<T> {

    protected static final String UID_FIELD = "uid";
    protected static final String DELETING_DT_FIELD = "deletingDT";

    protected static LocalDateTime getLocalDateTimeValue(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }

}
