package apap.tugasakhir.rumahsehat.security;

import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.repository.AdminDb;
import apap.tugasakhir.rumahsehat.repository.ApotekerDb;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private AdminDb adminDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = dokterDb.findByUsername(username);
        if (user == null) {
            user = apotekerDb.findByUsername(username);
            if (user == null) {
                user = adminDb.findByUsername(username);
            }
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
