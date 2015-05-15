package workingusers.service;

import workingusers.entity.UserEntity;
import workingusers.rest.UserForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Tomek on 2015-05-05.
 */
public interface UserService {

    Optional<UserEntity> getUserById(long id);

    Optional<UserEntity> getUserByEmail(String email);

    Collection<UserEntity> getAllUsers();

    UserEntity create(UserForm user);


}
