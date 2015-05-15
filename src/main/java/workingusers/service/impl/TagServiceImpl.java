package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import workingusers.entity.TagEntity;
import workingusers.repository.PostRepository;
import workingusers.repository.TagRepository;
import workingusers.rest.Tag;
import workingusers.service.TagService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tomek on 2015-05-05.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Tag> getAll() {
        List<TagEntity> tags = tagRepository.findAll();

        List<Tag> result = new ArrayList<Tag>();

        for (TagEntity t : tags) {
            result.add(new Tag(t.getId(),t.getTagname()));
        }

        return result;
    }

    @Override
    public void addNew(Tag tag) {
        TagEntity t = new TagEntity(null,tag.getName());
        tagRepository.save(t);
    }

    @Override
    public TagEntity getOneByName(String name) {
        return tagRepository.findOneByTagname(name);
    }

    @Override
    public HashMap<String, Long> getTagCloud() {
        HashMap<String, Long> result = new HashMap<String, Long>();
        List<TagEntity> tags = tagRepository.findAll();
        for (TagEntity tagEntity : tags) {
            result.put(tagEntity.getTagname(), postRepository.countByTags(tagEntity));
        }
        return result;
    }

    @Override
    public List<Tag> getAllByName(String name) {
        List<TagEntity> tags = tagRepository.findAllByTagnameLike(name);

        List<Tag> result = new ArrayList<Tag>();

        for (TagEntity t : tags) {
            result.add(new Tag(t.getId(),t.getTagname()));
        }
        return result;
    }
}
