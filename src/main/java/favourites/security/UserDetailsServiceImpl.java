package favourites.security;

import favourites.dao.DomainOperations;
import favourites.domain.EntityType;
import favourites.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

    private final DomainOperations domainOperations;

    @Autowired
    public UserDetailsServiceImpl(DomainOperations domainOperations) {
        this.domainOperations = domainOperations;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = domainOperations.findById(EntityType.USER, username);
        if (user == null) throw new UsernameNotFoundException(String.format("Username not found - %s", username));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}
