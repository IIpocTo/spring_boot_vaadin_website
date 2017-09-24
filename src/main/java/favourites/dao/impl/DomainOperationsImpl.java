package favourites.dao.impl;

import favourites.dao.DomainOperations;
import favourites.dao.Queries;
import favourites.dao.mappers.DomainMapper;
import favourites.domain.DomainObject;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DomainOperationsImpl implements DomainOperations<DomainObject> {

    private final JdbcTemplate jdbcTemplate;
    private final Queries queries;

    @Resource(name = "favouriteMapper")
    private DomainMapper<Favourite> favouriteMapper;

    @Resource(name = "userMapper")
    private DomainMapper<User> userMapper;

    @Autowired
    public DomainOperationsImpl(JdbcTemplate jdbcTemplate, Queries queries) {
        this.jdbcTemplate = jdbcTemplate;
        this.queries = queries;
    }

    @Override
    public void save(DomainObject domain) {

    }

    @Override
    public void update(DomainObject domain, String field) {

    }

    @Override
    public boolean remove(DomainObject entity) {
        return false;
    }

    @Override
    public DomainObject findById(boolean isFavourite, String uid) {
        return null;
    }

    @Override
    public List<? extends DomainObject> findAll(String username) {
        return jdbcTemplate.query(queries.getFavouriteQueries().getFindAllFavoriteQuery(),
                new Object[] { username }, favouriteMapper);
    }

}
