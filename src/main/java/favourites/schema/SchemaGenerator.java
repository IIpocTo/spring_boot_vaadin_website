package favourites.schema;

import favourites.dao.Queries;
import favourites.domain.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Component
public class SchemaGenerator {

    private final static String FV_TABLE_NAME = EntityType.FAVOURITE.getName();
 	private final static String[] FV_INDEXED_COLUMNS =
            new String[] { "NAME", "DELETING_DT", "ORDER_FV", "COUNTER", "USERNAME" };

    private final static String USER_TABLE_NAME = EntityType.USER.getName();
 	private final static String[] USER_INDEXED_COLUMNS =
            new String[] { "EMAIL", "DELETING_DT", "LAST_LOGGED" };

    private final JdbcTemplate jdbcTemplate;
    private final Queries queries;

    @Autowired
    public SchemaGenerator(JdbcTemplate jdbcTemplate, Queries queries) {
        this.jdbcTemplate = jdbcTemplate;
        this.queries = queries;
    }

    private void generateSchemaIfNotExists() {

        jdbcTemplate.execute(queries.getSchemaBuilder().getQueryToCreateTableUsers());
        Stream.of(USER_INDEXED_COLUMNS).forEach(columnName -> jdbcTemplate.execute(
                queries.getSchemaBuilder().getQueryToCreateIndex(USER_TABLE_NAME, columnName)));

        jdbcTemplate.execute(queries.getSchemaBuilder().getQueryToCreateTableFavourites());
        Stream.of(FV_INDEXED_COLUMNS).forEach(columnName -> jdbcTemplate.execute(
                queries.getSchemaBuilder().getQueryToCreateIndex(FV_TABLE_NAME, columnName)));

    }

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buildSchema() {
        this.generateSchemaIfNotExists();
    }

}
