package favourites.security;

import favourites.rest.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SecurityController {

    private final SecurityReference security;

    @Autowired
    public SecurityController(SecurityReference security) {
        this.security = security;
    }

    @PostMapping(SecurityReferenceImpl.PATH_LOGIN)
    void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        security.login(username, password);
    }

    @GetMapping(SecurityReferenceImpl.PATH_LOGOUT)
    @ResponseStatus(HttpStatus.OK)
    void logout() {
        security.logout();
    }

    @PostMapping(SecurityReferenceImpl.PATH_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody UserResource user) {
        security.register(user);
    }

}
