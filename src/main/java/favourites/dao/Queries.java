package favourites.dao;

import favourites.domain.DomainObject;
import favourites.domain.Favourite;
import favourites.domain.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public interface Queries {

    @NotNull @NotEmpty String getDeleteQuery(@NotNull DomainObject domain);
    @NotNull @NotEmpty String getFindQuery(@NotNull DomainObject domain);
    @NotNull @NotEmpty String getUpdateQuery(@NotNull DomainObject domain, @NotNull @NotEmpty String columnName);

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

}
