package favourites.dao;

import favourites.domain.DomainObject;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DomainOperations<T extends DomainObject> {
    void save(@NotNull T domain);
    void update(@NotNull T domain, @NotNull @NotEmpty String field);
    boolean remove(@NotNull T entity);
    T findById(boolean isFavourite, @NotNull String uid);
    @NotNull List<? extends T> findAll(@NotNull @NotEmpty String username);
}
