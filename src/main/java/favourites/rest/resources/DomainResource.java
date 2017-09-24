package favourites.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
