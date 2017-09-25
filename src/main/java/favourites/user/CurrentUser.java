package favourites.user;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public interface CurrentUser {
    @NotNull @NotEmpty String getUsername();
}
