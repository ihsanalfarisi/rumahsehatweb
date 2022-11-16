package apap.tugasakhir.rumahsehat.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userAuth = (User) auth.getPrincipal();
        Collection<GrantedAuthority> grantedAuthorities =  userAuth.getAuthorities();
        Optional<GrantedAuthority> optAuthority = grantedAuthorities.stream().findFirst();
        GrantedAuthority authority;
        if (optAuthority.isPresent()) {
            authority = optAuthority.get();
            return authority.getAuthority();
        } else return null;
    }
}
