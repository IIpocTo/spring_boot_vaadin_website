package favourites.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User extends DomainObject {

    private static final String ENTITY_NAME = "user";

    private String email;
    private LocalDate regDate;
    private String password;

    public User(String name, String email, String password) {
        super(name);
        this.password = password;
        this.regDate = LocalDate.now();
        this.email = email;
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

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

}
