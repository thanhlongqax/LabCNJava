package vn.edu.tdtu.lab09.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09.models.UserAccount;
import vn.edu.tdtu.lab09.models.UserDetail;
import vn.edu.tdtu.lab09.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email: " + username));
        return new UserDetail(userAccount);
    }
}
