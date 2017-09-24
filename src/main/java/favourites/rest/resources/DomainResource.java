package favourites.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import favourites.domain.DomainObject;
import org.hibernate.validator.constraints.NotEmpty;

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
    public abstract DomainObject convertToDomainObject(boolean isNew, @NotNull @NotEmpty String username);

}
