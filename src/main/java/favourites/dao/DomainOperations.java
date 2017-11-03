package favourites.dao;

import favourites.domain.DomainObject;
import favourites.domain.EntityType;
import favourites.domain.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;

public interface DomainOperations {
    void save(@NotNull DomainObject domain);
    void update(@NotNull EntityType entityType, @NotNull DomainObject current);
    boolean remove(@NotNull EntityType entityType, @NotNull String key);
    <T extends DomainObject> T findById(@NotNull EntityType entityType, @NotNull String uid);
    @NotNull List<? extends DomainObject> findAll(@NotNull @NotEmpty String username);
    @NotNull List<User> findUsers(@NotNull @Past LocalDateTime lastLogged);
}
