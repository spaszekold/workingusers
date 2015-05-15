package workingusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workingusers.entity.UserEntity;

import java.util.Optional;

/**
 * Created by Tomek on 2015-05-05.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneByEmail(String email);
    UserEntity findOneByNick(String nick);
    Optional<UserEntity> findOneById(long id);

}
