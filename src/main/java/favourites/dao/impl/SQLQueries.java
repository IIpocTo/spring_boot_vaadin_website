package favourites.dao.impl;

import favourites.dao.Queries;
import favourites.domain.EntityType;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("sqlQueries")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SQLQueries implements Queries {

    @Override
    public String getDeleteQuery(EntityType entity) {
        return Patterns.replacePlaceholders(entity, Patterns.DELETE_QUERY);
    }

    @Override
    public String getFindQuery(EntityType entity) {
        return Patterns.replacePlaceholders(entity, Patterns.FIND_QUERY);
    }

    @Override
    public String getUpdateQuery(EntityType entity, String columnName) {
        return Patterns.replaceUpdatePlaceholders(entity, columnName, Patterns.UPDATE_QUERY);
    }

    @Override
    public FavouriteQueries getFavouriteQueries() {
        return new FavouriteQueries() {
            @Override
            public String getInsertFavoriteQuery(Favourite favourite) {
                return Patterns.INSERT_FAVOURITE_QUERY;
            }

            @Override
            public String getFindAllFavoriteQuery() {
                return Patterns.FIND_FAVOURITES_QUERY;
            }
        };
    }

    @Override
    public UserQueries getUserQueries() {
        return new UserQueries() {
            @Override
            public String getInsertUserQuery(User user) {
                return Patterns.INSERT_USER_QUERY;
            }

            @Override
            public String getFindAllUserQuery() {
                return Patterns.FIND_USERS_QUERY;
            }
        };
    }

    @Override
    public SchemaBuilder getSchemaBuilder() {
        return new SchemaBuilder() {

            @Override
            public String getQueryToCreateTableFavourites() {
                return Patterns.CREATE_FV_TABLE;
            }

            @Override
            public String getQueryToCreateTableUsers() {
                return Patterns.CREATE_USER_TABLE;
            }

            @Override
            public String getQueryToCreateIndex(String tableName, String columnName) {
                return Patterns.replaceDdlPlaceholders(tableName, columnName, Patterns.CREATE_INDEX_TABLE);
            }

        };
    }

    final static class Patterns {

        static final String TABLE_NAME_PLACEHOLDER = "<tableName>";
        static final String COLUMN_NAME_PLACEHOLDER = "<columnName>";

        static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME_PLACEHOLDER + " WHERE uid = ?";
        static final String FIND_QUERY = "SELECT * FROM " + TABLE_NAME_PLACEHOLDER + " WHERE uid = ?";
        static final String UPDATE_QUERY = "UPDATE " + TABLE_NAME_PLACEHOLDER +
                " SET " + COLUMN_NAME_PLACEHOLDER + " = ?";

        static final String INSERT_FAVOURITE_QUERY = "INSERT INTO favourite " +
                "(uid, name, link, addingDT, deletingDT, order, counter, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        static final String INSERT_USER_QUERY = "INSERT INTO user_t " +
                "(username, email, regDT, password, deletingDT) VALUES (?, ?, ?, ?, ?)";

        static final String FIND_FAVOURITES_QUERY = "SELECT * FROM favourite WHERE username = ?";
 		static final String FIND_USERS_QUERY = "SELECT * FROM user_t";

        static final String CREATE_FV_TABLE = "CREATE TABLE IF NOT EXISTS FAVOURITE (UID varchar(64) NOT NULL, "
                + "NAME varchar(256) NOT NULL, LINK varchar(512) NOT NULL, ADDING_DT timestamp NOT NULL, "
                + "DELETING_DT timestamp NULL, ORDER_FV int NOT NULL, COUNTER int NOT NULL, "
                + "USERNAME varchar(64) NOT NULL references USER_T(UID), PRIMARY KEY (UID));";

        static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS USER_T (UID varchar(64) NOT NULL, "
                + "EMAIL varchar(128) NOT NULL, PASSWORD varchar(64) NOT NULL, REG_DATE date NOT NULL, "
                + "DELETING_DT timestamp NULL, LAST_LOGGED timestamp NOT NULL, PRIMARY KEY (UID));";

        static final String CREATE_INDEX_TABLE = "CREATE INDEX IF NOT EXISTS idx_" + COLUMN_NAME_PLACEHOLDER +
                " ON " + TABLE_NAME_PLACEHOLDER + " (" + COLUMN_NAME_PLACEHOLDER + ");";

 		static String replacePlaceholders(EntityType entity, String sourceQuery) {
 		    return sourceQuery.replace(TABLE_NAME_PLACEHOLDER, entity.getName());
        }

        static String replaceUpdatePlaceholders(EntityType entity, String columnName, String sourceQuery) {
 		    return sourceQuery.replace(TABLE_NAME_PLACEHOLDER, entity.getName())
                    .replace(COLUMN_NAME_PLACEHOLDER, columnName);
        }

        static String replaceDdlPlaceholders(String tableName, String columnName, String sourceQuery) {
            return sourceQuery.replace(TABLE_NAME_PLACEHOLDER, tableName)
                    .replace(COLUMN_NAME_PLACEHOLDER, columnName);
        }

    }

}
