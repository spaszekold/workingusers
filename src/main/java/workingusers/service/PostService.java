package workingusers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import workingusers.entity.PostEntity;
import workingusers.entity.TagEntity;
import workingusers.rest.PostFront;
import workingusers.rest.PostFull;

import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
public interface PostService {
   // public List<PostFront> getFront();
    public PostFull getFull(long id);

    public Page<PostEntity> getAllByTag(String tagname, Pageable pageable);

    public Page<PostEntity> getAllByUser(String username, Pageable pageable);


    public Page<PostEntity> getAllFrontPosts(Pageable pageable);

    void addNewPost(PostFull postFull,Authentication authentication);
}
