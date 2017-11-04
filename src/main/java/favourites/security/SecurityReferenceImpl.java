package favourites.security;

import favourites.domain.EntityType;
import favourites.rest.controllers.RESTOperations;
import favourites.rest.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityReferenceImpl implements SecurityReference {

    public static final String PATH_LOGIN = "/login";
    public static final String PATH_LOGOUT = "/logout";
    public static final String PATH_REGISTER = "/register";

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RESTOperations operations;

    @Autowired
    public SecurityReferenceImpl(UserDetailsService userDetailsService,
                                 AuthenticationManager authenticationManager,
                                 RESTOperations operations) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.operations = operations;
    }

    @Override
    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            final UserResource user = (UserResource) operations.readById(username, EntityType.USER.getName());
            user.loggingLogin();
            operations.update(EntityType.USER.getName(), user);
        } else {
            throw new SecurityException("User can't be authenticate.");
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }

    @Override
    public void register(UserResource user) {
        operations.create(EntityType.USER.getName(), user, null);
    }

}
