package favourites.dao.mappers;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class DomainMapper<T> implements RowMapper<T>, ResultSetExtractor<T> {

    public static final String UID_FIELD = "uid";
    public static final String DELETING_DT_FIELD = "deletingDT";

    static LocalDateTime getLocalDateTimeValue(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }

}
