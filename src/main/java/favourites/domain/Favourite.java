package favourites.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Favourite extends DomainObject {

    @NotNull private String name;
    @NotNull private String link;
    @NotNull private Long counter;
    @NotNull private Integer order;
    @NotNull private LocalDateTime addingDT;
    private String userName;

    public Favourite(String link, String name, String username) {
        super(link.hashCode() + name.hashCode());
        this.link = link;
        this.name = name;
        this.userName = username;
        this.addingDT = LocalDateTime.now();
    }

    public Favourite(String uid, String username, LocalDateTime addingDT, LocalDateTime deletingDT) {
        super(uid, deletingDT);
        this.addingDT = addingDT;
        this.userName = username;
    }

    public Favourite(String link, String name, String uid,
                     String username, LocalDateTime addingDT, LocalDateTime deletingDT) {
        this(uid, username, addingDT, deletingDT);
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public LocalDateTime getAddingDT() {
        return addingDT;
    }

    public String getUserId() {
        return userName;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public String getEntityName() {
        return EntityType.FAVOURITE.getName();
    }

}
