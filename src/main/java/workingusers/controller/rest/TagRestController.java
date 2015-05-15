package workingusers.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workingusers.rest.Tag;
import workingusers.service.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 2015-05-05.
 */
@RestController
@RequestMapping(value = "/api2/tag")
public class TagRestController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public boolean addNew(@RequestBody Tag tag) {
        tagService.addNew(tag);
        return true;
    }

    @RequestMapping(value = "/getAllByName/{name}", method = RequestMethod.GET)
    public List<String> getAllByName(@PathVariable(value = "name") String name) {
        List<Tag> t = tagService.getAllByName(name);
        List<String> result = new ArrayList<String>();
        for (Tag tag : t) {
            result.add(new String(tag.getName()));
        }
        return result;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Tag> getAll() {
        List<Tag> res = tagService.getAll();
        System.out.println("zwracam tagi:" + res);
        return res;
    }

}
