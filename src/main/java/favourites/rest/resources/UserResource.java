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
    @NotNull private LocalDateTime lastLogged;

    @JsonCreator
    public UserResource(@JsonProperty("uid") String uid, @JsonProperty("deletingDT") LocalDateTime deletingDT,
                        @JsonProperty("email") String email, @JsonProperty("lastLogged") LocalDateTime lastLogged,
                        @JsonProperty("regDate") LocalDate regDate, @JsonProperty("password") String password) {
        super(uid, deletingDT, deletingDT != null);
        this.email = email;
        this.regDate = regDate;
        this.password = password;
        this.lastLogged = lastLogged;
    }

    public UserResource(User user) {
        super(user);
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.password = user.getPassword();
        this.lastLogged = user.getLastLoggedDT();
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("regDate")
    public LocalDate getRegDate() {
        return regDate;
    }

    @JsonProperty("lastLogged")
    public LocalDateTime getLastLogged() {
        return lastLogged;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void loggingLogin() {
        this.lastLogged = LocalDateTime.now();
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
            user.setLastLogged(this.lastLogged);
        }
        return user;
    }

}
