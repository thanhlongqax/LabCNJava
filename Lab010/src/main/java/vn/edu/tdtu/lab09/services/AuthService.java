package vn.edu.tdtu.lab09.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09.config.JwtUtils;
import vn.edu.tdtu.lab09.dto.JwtResDTO;
import vn.edu.tdtu.lab09.dto.ResDTO;
import vn.edu.tdtu.lab09.dto.SignInReqDTO;
import vn.edu.tdtu.lab09.dto.SignUpResDTO;
import vn.edu.tdtu.lab09.models.UserAccount;
import vn.edu.tdtu.lab09.repositories.UserRepository;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    public ResDTO signUpAccount(UserAccount userAccount){
        if(!repository.existsByEmail(userAccount.getEmail())){
            userAccount.setRole(
                    userAccount.getRole() == null || userAccount.getRole().isEmpty()
                    ? "ROLE_USER" : userAccount.getRole()
            );
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            UserAccount addedUserAccount = repository.save(userAccount);
            return new ResDTO(
                    "Sign up successfully",
                    true,
                    new SignUpResDTO(addedUserAccount.getId(), addedUserAccount.getEmail())
            );
        }
        return new ResDTO(
                "User email is already taken",
                false,
                null
        );
    }

    public ResDTO signIn(SignInReqDTO credentials) throws Exception {
        try {
            daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword()
                )
            );
        }catch (BadCredentialsException exception){
            throw new Exception("INVALID_CREDENTIALS", exception);
        }

        UserDetails userDetail = userDetailsService.loadUserByUsername(credentials.getEmail());
        String token = jwtUtils.generateJwtToken(userDetail);

        return new ResDTO(
                "Sign in successfully",
                true,
                new JwtResDTO(
                        userDetail.getUsername(),
                        token,
                        ((List) userDetail.getAuthorities()).get(0).toString()
                )
        );
    }
}
