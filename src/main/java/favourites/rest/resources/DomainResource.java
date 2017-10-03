package favourites.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import favourites.domain.DomainObject;
import favourites.domain.Favourite;
import favourites.domain.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class DomainResource implements Serializable {

    private static final long serialVersionUID = -35547347675674L;

    private final String uid;
    private final LocalDateTime deletingDT;
    private final boolean isDeleted;

    @JsonCreator
    public DomainResource(@JsonProperty("uid") String uid,
                          @JsonProperty("deletingDT") LocalDateTime deletingDT,
                          @JsonProperty("isDeleted") boolean isDeleted) {
        this.uid = uid;
        this.deletingDT = deletingDT;
        this.isDeleted = isDeleted;
    }

    public DomainResource(DomainObject domain) {
        this.uid = domain.getUid();
        this.deletingDT = domain.getDeletingDT();
        this.isDeleted = domain.isDeleted();
    }

    @JsonIgnore
    public static @NotNull DomainResource convertToResource(@NotNull DomainObject domain) {
        if (domain instanceof Favourite) return new FavouriteResource((Favourite) domain);
        else if (domain instanceof User) return new UserResource((User) domain);
        else throw new IllegalArgumentException();
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("deletingDT")
    public LocalDateTime getDeletingDT() {
        return deletingDT;
    }

    @JsonProperty("isDeleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonIgnore
    public abstract @NotNull DomainObject convertToDomainObject(boolean isNew, String username);

}
