package favourites.dao.impl;

import favourites.dao.DomainOperations;
import favourites.dao.Queries;
import favourites.domain.DomainObject;
import favourites.domain.EntityType;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DomainOperationsImpl implements DomainOperations {

    private final JdbcTemplate jdbcTemplate;
    private final Queries queries;

    @Resource(name = "favouriteMapper")
    private RowMapper<Favourite> favouriteMapper;

    @Resource(name = "userMapper")
    private RowMapper<User> userMapper;

    @Resource(name = "favouriteMapper")
    private ResultSetExtractor<Favourite> favouriteExtractor;

    @Resource(name = "userMapper")
    private ResultSetExtractor<User> userExtractor;

    @Autowired
    public DomainOperationsImpl(JdbcTemplate jdbcTemplate, Queries queries) {
        this.jdbcTemplate = jdbcTemplate;
        this.queries = queries;
    }

    @Override
    public void save(DomainObject domain) {
        if (domain instanceof Favourite) {
            Favourite favourite = (Favourite) domain;
            final LocalDateTime deletingDT = favourite.getDeletingDT();
            jdbcTemplate.update(queries.getFavouriteQueries().getInsertFavoriteQuery(favourite), ps -> {
                ps.setString(1, favourite.getUid());
                ps.setString(2, favourite.getName());
                ps.setString(3, favourite.getLink());
                ps.setTimestamp(4, Timestamp.valueOf(favourite.getAddingDT()));
                ps.setTimestamp(5, deletingDT == null ? null : Timestamp.valueOf(deletingDT));
                ps.setInt(6, favourite.getOrder());
                ps.setLong(7, favourite.getCounter());
            });
        } else if (domain instanceof User) {
            User user = (User) domain;
            final LocalDateTime deletingDT = user.getDeletingDT();
            jdbcTemplate.update(queries.getUserQueries().getInsertUserQuery(user), ps -> {
               ps.setString(1, user.getUid());
               ps.setString(2, user.getEmail());
               ps.setDate(3, Date.valueOf(user.getRegDate()));
               ps.setString(4, user.getPassword());
               ps.setTimestamp(5, deletingDT == null ? null : Timestamp.valueOf(deletingDT));
               ps.setTimestamp(6, user.getLastLogged() == null ? null : Timestamp.valueOf(user.getLastLogged()));
            });
        }
    }

    @Override
    public void update(EntityType entityType, String field, Object fieldValue) {
        jdbcTemplate.update(queries.getUpdateQuery(entityType, field), ps -> paramTypeSetter(ps, fieldValue, 1));
    }

    @Override
    public boolean remove(EntityType entityType, String key) {
        return jdbcTemplate.update(queries.getDeleteQuery(entityType), ps -> ps.setString(1, key)) > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DomainObject> T findById(EntityType entityType, String uid) {
        if (entityType == EntityType.FAVOURITE) {
            return jdbcTemplate.query(queries.getFindQuery(entityType), new Object[] { uid },
                    (ResultSetExtractor<T>) favouriteExtractor);
        } else if (entityType == EntityType.USER) {
            return jdbcTemplate.query(queries.getFindQuery(entityType), new Object[] { uid },
                    (ResultSetExtractor<T>) userExtractor);
        } else return null;
    }

    @Override
    public List<? extends DomainObject> findAll(String username) {
        return jdbcTemplate.query(queries.getFavouriteQueries().getFindAllFavoriteQuery(),
                new Object[] { username }, favouriteMapper);
    }

    private static void paramTypeSetter(PreparedStatement ps, Object value, int position) {
        try {
            if (value instanceof String) ps.setString(position, (String) value);
            else if (value instanceof Timestamp) ps.setTimestamp(position, (Timestamp) value);
            else if (value instanceof LocalDateTime) ps.setTimestamp(position, Timestamp.valueOf((LocalDateTime) value));
            else if (value instanceof Date) ps.setDate(position, (Date) value);
            else if (value instanceof Integer) ps.setInt(position, (Integer) value);
            else if (value instanceof Long) ps.setLong(position, (Long) value);
            else throw new RuntimeException("Unknown type.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
