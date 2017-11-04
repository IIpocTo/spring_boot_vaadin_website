package favourites.security;

import favourites.rest.resources.UserResource;

import javax.validation.constraints.NotNull;

public interface SecurityReference {
    void login(@NotNull String username, @NotNull String password);
    void logout();
    void register(@NotNull UserResource user);
}
