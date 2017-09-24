package favourites.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public abstract class DomainObject {

    private boolean isNew;
    private boolean isDeleted;
    private LocalDateTime deletingDT;
    protected String uid;

    protected DomainObject(String uid, LocalDateTime deletingDT) {
        this.deletingDT = deletingDT;
        this.uid = uid;
        this.isNew = false;
        this.isDeleted = deletingDT != null;
    }

    protected DomainObject(@NotNull String uid) {
        this.uid = uid;
        this.deletingDT = null;
        this.isNew = true;
        this.isDeleted = false;
    }

    protected DomainObject(int uid) {
        this(Integer.toString(uid));
    }

    public abstract String getUid();
    public abstract String getEntityName();

    public boolean isNew() {
        return isNew;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getDeletingDT() {
        return deletingDT;
    }

}
