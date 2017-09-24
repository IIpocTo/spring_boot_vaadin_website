package favourites.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FavouriteResource extends DomainResource {

    private static final long serialVersionUID = 743532432432432432L;

    @NotNull @NotEmpty private final String name;
    @NotNull @NotEmpty private final String link;
    @NotNull private final Long counter;
    @NotNull private final Integer order;
    @NotNull private final LocalDateTime addingDT;

    public FavouriteResource(@JsonProperty("uid") String uid, @JsonProperty("deletingDT") LocalDateTime deletingDT,
                             @JsonProperty("isDeleted") boolean isDeleted, @JsonProperty("name") String name,
                             @JsonProperty("link") String link, @JsonProperty("addingDT") Long counter,
                             @JsonProperty("order") Integer order, @JsonProperty("counter") LocalDateTime addingDT) {
        super(uid, deletingDT, isDeleted);
        this.name = name;
        this.link = link;
        this.counter = counter;
        this.order = order;
        this.addingDT = addingDT;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("counter")
    public Long getCounter() {
        return counter;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("addingDT")
    public LocalDateTime getAddingDT() {
        return addingDT;
    }

}
