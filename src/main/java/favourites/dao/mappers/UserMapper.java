package favourites.dao.mappers;

import favourites.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("userMapper")
public class UserMapper extends DomainMapper<User> {

    public static final String EMAIL_FIELD = "email";
    public static final String REG_DT_FIELD = "regDate";
    public static final String PASSWORD_FIELD = "password";
    public static final String LAST_LOGGED_FIELD = "lastLogged";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString(DomainMapper.UID_FIELD),
                getLocalDateTimeValue(rs.getTimestamp(DomainMapper.DELETING_DT_FIELD)),
                rs.getString(PASSWORD_FIELD));
        user.setEmail(rs.getString(EMAIL_FIELD));
        user.setRegDate(rs.getDate(REG_DT_FIELD).toLocalDate());
        user.setLastLogged(getLocalDateTimeValue(rs.getTimestamp(LAST_LOGGED_FIELD)));
        return user;
    }

    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        return mapRow(rs, 0);
    }

}
