package favourites.dao.mappers;

import favourites.domain.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("userMapper")
public class UserMapper extends DomainMapper<User> {

    private static final String EMAIL_FIELD = "email";
 	private static final String REG_DT_FIELD = "regDate";
 	private static final String PASSWORD_FIELD = "password";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString(DomainMapper.UID_FIELD),
                getLocalDateTimeValue(rs.getTimestamp(DomainMapper.DELETING_DT_FIELD)),
                rs.getString(PASSWORD_FIELD));
        user.setEmail(rs.getString(EMAIL_FIELD));
        user.setRegDate(rs.getDate(REG_DT_FIELD).toLocalDate());
        return user;
    }

}
