package workingusers.rest;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import workingusers.entity.UserEntity;

/**
 * Created by Tomek on 2015-05-05.
 */
public class CurrentUser extends User {

    private UserEntity userEntity;

    public CurrentUser(UserEntity userEntity) {
        super(userEntity.getEmail(),userEntity.getPasswordHash(),AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public long getId() {
        return userEntity.getId();
    }

    public Role getRole() {
        return userEntity.getRole();
    }


}
