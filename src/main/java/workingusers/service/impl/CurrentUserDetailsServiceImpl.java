package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import workingusers.entity.UserEntity;
import workingusers.rest.CurrentUser;
import workingusers.service.CurrentUserDetailsService;
import workingusers.service.UserService;

/**
 * Created by Tomek on 2015-05-05.
 */
@Service
public class CurrentUserDetailsServiceImpl implements CurrentUserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new CurrentUser(user);
    }
}
