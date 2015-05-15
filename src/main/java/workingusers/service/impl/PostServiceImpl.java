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

//    @Override
//    public List<PostFront> getFront() {
//        List<PostFront> result = new ArrayList<PostFront>();
//        List<PostEntity> entities = (List<PostEntity>) postRepository.findAllByOrderByCreatedDesc();
//
//
//        System.out.println(entities);
//        for (PostEntity p : entities) {
//            Set<Tag> tags = new HashSet<Tag>();
//            for (TagEntity t: p.getTags()) {
//                Tag newtag = new Tag(t.getId(),t.getTagname());
//                tags.add(newtag);
//            }
//
//            Optional<UserEntity> userEntity = null;
//            Author author;
//            if (p.getUserid() != null) {
//                userEntity = userRepository.findOneById(p.getUserid().getId());
//                if (userEntity.isPresent())
//                    author = new Author(userEntity.get().getId(),userEntity.get().getNick());
//                else
//                    author = new Author();
//
//            }
//            else
//                author = new Author();
//
//
//
//
//            PostFront n = new PostFront(p.getId(),p.getFullname(),p.getLilname(),p.getCreated(),tags,author);
//            result.add(n);
//        }
//
//
//        return result;
//    }

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
        System.out.println("nowy post wjezdza");
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
            System.out.println("stworzyl go " + userEntity);
            p.setUserid(userEntity.get());
        }

        postRepository.save(p);
    }
}
