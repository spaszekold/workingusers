package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import workingusers.entity.PostEntity;
import workingusers.entity.TagEntity;
import workingusers.entity.UserEntity;
import workingusers.repository.PostRepository;
import workingusers.repository.TagRepository;
import workingusers.repository.UserRepository;
import workingusers.rest.Author;
import workingusers.rest.PostFront;
import workingusers.rest.PostFull;
import workingusers.rest.Tag;
import workingusers.service.PostService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

/**
 * Created by Tomek on 2015-04-30.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostFull getFull(long id) {
        PostEntity result = postRepository.findOneById(id);
        return new PostFull(result.getContent(),result.getCreated(),result.getFullname(),result.getId(),result.getLilname());
    }

    @Override
    public Page<PostEntity> getAllByTag(String tagname, Pageable pageable) {
        TagEntity tagEntity = tagRepository.findOneByTagname(tagname);
        return postRepository.findAllByTagsOrderByCreatedDesc(tagEntity,pageable);
    }

    @Override
    public Page<PostEntity> getAllByUser(String username, Pageable pageable) {
        UserEntity userEntity = userRepository.findOneByNick(username);
        return postRepository.findAllByUseridOrderByCreatedDesc(userEntity,pageable);
    }

    @Override
    public Page<PostEntity> getAllFrontPosts(Pageable pageable) {
       return postRepository.findAllByOrderByCreatedDesc(pageable);


    }

    @Override
    public void addNewPost(PostFull postFull, Authentication authentication) {
        Set<TagEntity> tags = new HashSet<>();
        if (postFull.getTags() != null) {
            for (String s : postFull.getTags()) {
                TagEntity tagEntity = tagRepository.findOneByTagname(s);
                if (tagEntity == null) {
                    tagEntity = new TagEntity(null,s);
                    tagRepository.save(tagEntity);
                }


                tags.add(tagEntity);
            }
        }
        PostEntity p = new PostEntity(postFull.getLilname(),postFull.getContent(),new Date(),postFull.getFullname(),tags);

        UserDetails user = (UserDetails) authentication.getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findOneByEmail(user.getUsername());

        if (userEntity.isPresent()) {
            p.setUserid(userEntity.get());
        }

        postRepository.save(p);
    }
}
