package com.kasperovich.security;

import com.kasperovich.models.Role;
import com.kasperovich.models.User;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(isValid(username)){
            try {
                /*Find user in DB*/
                Optional<User> searchResult = userRepository.findUserByEmail(username);

                if (searchResult.isPresent()) {
                    User user = searchResult.get();

                    /*We are creating Spring Security User object*/
                    return new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getCredentials().getPassword(),
                            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName().toString()+",")
                    );
                } else {
                    throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
                }
            } catch (Exception e) {
                throw new UsernameNotFoundException("User with this email not found");
            }
        }
        try {
            /*Find user in DB*/
            Optional<User> searchResult = userRepository.findUserByCredentialsLogin(username);

            if (searchResult.isPresent()) {
                User user = searchResult.get();

                /*We are creating Spring Security User object*/
                return new org.springframework.security.core.userdetails.User(
                        user.getCredentials().getLogin(),
                        user.getCredentials().getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName().toString()+",")
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }


    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
