package workingusers.service;

import workingusers.entity.TagEntity;
import workingusers.rest.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomek on 2015-05-05.
 */
public interface TagService {
    public List<Tag> getAll();

    void addNew(Tag tag);


    TagEntity getOneByName(String name);


    HashMap<String, Long> getTagCloud();

    List<Tag> getAllByName(String name);
}
