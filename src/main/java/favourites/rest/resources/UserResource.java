package favourites.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import favourites.domain.DomainObject;
import favourites.domain.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends DomainResource {

    private static final long serialVersionUID = -63432432433342L;

    @NotNull @NotEmpty private final String email;
    @NotNull private final LocalDate regDate;
    @NotNull @NotEmpty private final String password;

    @JsonCreator
    public UserResource(@JsonProperty("uid") String uid, @JsonProperty("deletingDT") LocalDateTime deletingDT,
                        @JsonProperty("isDeleted") boolean isDeleted, @JsonProperty("email") String email,
                        @JsonProperty("regDate") LocalDate regDate, @JsonProperty("password") String password) {
        super(uid, deletingDT, isDeleted);
        this.email = email;
        this.regDate = regDate;
        this.password = password;
    }

    public UserResource(User user) {
        super(user);
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.password = user.getPassword();
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("regDate")
    public LocalDate getRegDate() {
        return regDate;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public DomainObject convertToDomainObject(boolean isNew, String username) {
        final User user;
        if (isNew) {
            user = new User(getUid(), this.email, this.password);
        } else {
            user = new User(getUid(), getDeletingDT(), this.password);
            user.setEmail(this.email);
            user.setRegDate(this.regDate);
        }
        return user;
    }

}
