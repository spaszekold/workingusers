package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workingusers.entity.UserEntity;
import workingusers.repository.UserRepository;
import workingusers.rest.Role;
import workingusers.rest.UserForm;
import workingusers.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Tomek on 2015-05-05.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<UserEntity> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public UserEntity create(UserForm user) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        UserEntity newuser = new UserEntity();
        newuser.setEmail(user.email);
        newuser.setNick(user.nick);
        newuser.setRole(Role.ROLE_ADMIN);
        newuser.setPasswordHash(b.encode(user.password));
        return userRepository.save(newuser);
    }
}
