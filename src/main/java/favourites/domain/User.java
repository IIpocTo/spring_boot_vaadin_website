package favourites.domain;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User extends DomainObject {

    @NotNull @Email private String email;
    @NotNull private LocalDate regDate;
    @NotNull private String password;
    @NotNull private LocalDateTime lastLogged;

    public User(String name, String email, String password) {
        super(name);
        this.password = password;
        this.regDate = LocalDate.now();
        this.email = email;
        this.lastLogged = LocalDateTime.now();
    }

    public User(String name, LocalDateTime deletingDT, String password) {
        super(name, deletingDT);
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLastLoggedDT() {
        return lastLogged;
    }

    public void setLastLogged(LocalDateTime lastLogged) {
        this.lastLogged = lastLogged;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public String getEntityName() {
        return EntityType.USER.getName();
    }

}
