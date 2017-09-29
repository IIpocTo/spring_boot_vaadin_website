package favourites.dao;

import favourites.domain.EntityType;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public interface Queries {

    @NotNull @NotEmpty String getDeleteQuery(@NotNull EntityType entity);
    @NotNull @NotEmpty String getFindQuery(@NotNull EntityType entity);
    @NotNull @NotEmpty String getUpdateQuery(@NotNull EntityType entity, @NotNull @NotEmpty String columnName);

    interface FavouriteQueries {
        @NotNull @NotEmpty String getInsertFavoriteQuery(@NotNull Favourite favourite);
        @NotNull @NotEmpty String getFindAllFavoriteQuery();
    }

    interface UserQueries {
        @NotNull @NotEmpty String getInsertUserQuery(@NotNull User user);
        @NotNull @NotEmpty String getFindAllUserQuery();
    }

    @NotNull FavouriteQueries getFavouriteQueries();
    @NotNull UserQueries getUserQueries();

    interface SchemaBuilder {
        @NotNull @NotEmpty
        String getQueryToCreateTableFavourites();
        @NotNull @NotEmpty
        String getQueryToCreateTableUsers();
        @NotNull @NotEmpty
        String getQueryToCreateIndex(@NotNull @NotEmpty String tableName, @NotNull @NotEmpty String columnName);
    }

    @NotNull SchemaBuilder getSchemaBuilder();

}
