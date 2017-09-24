package favourites.dao.impl;

import favourites.dao.Queries;
import favourites.domain.DomainObject;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("sqlQueries")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SQLQueries implements Queries {

    @Override
    public String getDeleteQuery(DomainObject domain) {
        return Patterns.replacePlaceholders(domain, Patterns.DELETE_QUERY);
    }

    @Override
    public String getFindQuery(DomainObject domain) {
        return Patterns.replacePlaceholders(domain, Patterns.FIND_QUERY);
    }

    @Override
    public String getUpdateQuery(DomainObject domain, String columnName) {
        return Patterns.replaceUpdatePlaceholders(domain, columnName, Patterns.UPDATE_QUERY);
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

    final static class Patterns {
        static final String TABLE_NAME_PLACEHOLDER = "<tableName>";
        static final String COLUMN_NAME_PLACEHOLDER = "<columnName>";

        static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME_PLACEHOLDER + " WHERE uid = ?";
        static final String FIND_QUERY = "SELECT * FROM " + TABLE_NAME_PLACEHOLDER + " WHERE uid = ?";
        static final String UPDATE_QUERY = "UPDATE " + TABLE_NAME_PLACEHOLDER +
                " SET " + COLUMN_NAME_PLACEHOLDER + " =?";

        static final String INSERT_FAVOURITE_QUERY = "INSERT INTO favourite " +
                "(uid, name, link, addingDT, deletingDT, order, counter, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        static final String INSERT_USER_QUERY = "INSERT INTO favourites.user " +
                "(username, email, regDT, password, deletingDT) VALUES (?, ?, ?, ?, ?)";

        static final String FIND_FAVOURITES_QUERY = "SELECT * FROM favourite WHERE username = ?";
 		static final String FIND_USERS_QUERY = "SELECT * FROM favourites.user";

 		static String replacePlaceholders(DomainObject domain, String sourceQuery) {
 		    return sourceQuery.replace(TABLE_NAME_PLACEHOLDER, domain.getEntityName());
        }

        static String replaceUpdatePlaceholders(DomainObject domain, String columnName, String sourceQuery) {
 		    return sourceQuery.replace(TABLE_NAME_PLACEHOLDER, domain.getEntityName())
                    .replace(COLUMN_NAME_PLACEHOLDER, columnName);
        }

    }

}
