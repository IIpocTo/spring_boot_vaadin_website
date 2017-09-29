package favourites.dao.mappers;

import favourites.domain.Favourite;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("favouriteMapper")
public class FavouriteMapper extends DomainMapper<Favourite> {

    private static final String NAME_FIELD = "name";
    private static final String LINK_FIELD = "link";
    private static final String ADDING_DT_FIELD = "addingDT";
    private static final String USERNAME_FIELD = "username";
    private static final String ORDER_FIELD = "order_fv";
    private static final String COUNTER_FIELD = "counter";

    @Override
    public Favourite mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Favourite favourite = new Favourite(rs.getString(DomainMapper.UID_FIELD), rs.getString(USERNAME_FIELD),
                getLocalDateTimeValue(rs.getTimestamp(ADDING_DT_FIELD)),
                getLocalDateTimeValue(rs.getTimestamp(DomainMapper.DELETING_DT_FIELD)));
        favourite.setName(rs.getString(NAME_FIELD));
        favourite.setLink(rs.getString(LINK_FIELD));
        favourite.setOrder(rs.getInt(ORDER_FIELD));
        favourite.setCounter(rs.getLong(COUNTER_FIELD));
        return favourite;
    }

    @Override
    public Favourite extractData(ResultSet rs) throws SQLException, DataAccessException {
        return mapRow(rs, 0);
    }

}
