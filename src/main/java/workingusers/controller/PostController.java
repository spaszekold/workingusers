package workingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import workingusers.entity.PostEntity;
import workingusers.service.PostService;
import workingusers.service.TagService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * controller handling post rendering, by tags, user
 */
@Controller
@RequestMapping(value = "/post/")
public class PostController {


   /**
    * attribute added to each view, representing 10 most popular tags
    * returns Map<String,Long> where String equals to tag name
    *                             Long equals to number of posts with that tag
    */
    @ModelAttribute("tagCloud")
    public Map<String,Long> getTagCloud() {
        return tagService.getTagCloud()
                .entrySet().stream()                                                //GET ENTRYSET AND OPEN STREAM
                .sorted((o1, o2) -> o1.getValue() <= o2.getValue() ? 1 : -1)        //SORT BY VALUES, DESCENDING
                .filter(e -> e.getValue() > 0)                                      //GRAB ONLY THOSE WITH POST COUNT > 0
                .limit(10)                                                          //GET TOP 10
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,   //SAVE RESULTS TO MAP<STRING,LONG>
                        (e1, e2) -> e1, LinkedHashMap::new));
    }


    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    /**
      * returns view containing all posts by certain tag
      */
    @RequestMapping(value = "/tag/{tagname}/{pageNumber}", method = RequestMethod.GET)
    public String seeTagPage(@PathVariable String tagname, @PathVariable int pageNumber, Model uiModel) {
        PageRequest newPgb = new PageRequest(pageNumber - 1, 5);
        Page<PostEntity> currentResults = postService.getAllByTag(tagname,newPgb);
        uiModel.addAttribute("currentResults",currentResults);


        // attributes used by pagination
        int totalPages = currentResults.getTotalPages();
        // currently viewed page
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 5, currentResults.getTotalPages());
        String url = "/post/tag/" + tagname;
        String leaddescription = "browsing only tag #" +tagname;
        uiModel.addAttribute("leaddescription",leaddescription);
        uiModel.addAttribute("url", url);
        uiModel.addAttribute("totalPages",totalPages);
        uiModel.addAttribute("beginIndex", begin);
        uiModel.addAttribute("endIndex", end);
        uiModel.addAttribute("currentIndex", current);
        return "index";
    }

    /**
     * returns view containing all posts by certain user
     */
    @RequestMapping(value = "/user/{username}/{pageNumber}", method = RequestMethod.GET)
    public String seeUserPage(@PathVariable String username, @PathVariable int pageNumber, Model uiModel) {
        PageRequest newPgb = new PageRequest(pageNumber - 1, 5);
        Page<PostEntity> currentResults = postService.getAllByUser(username,newPgb);
        uiModel.addAttribute("currentResults",currentResults);
        int totalPages = currentResults.getTotalPages();
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 5, currentResults.getTotalPages());
        String url = "/post/user/" + username;
        String leaddescription = "browsing only articles written by " + username;
        uiModel.addAttribute("leaddescription",leaddescription);
        uiModel.addAttribute("url", url);
        uiModel.addAttribute("totalPages",totalPages);
        uiModel.addAttribute("beginIndex", begin);
        uiModel.addAttribute("endIndex", end);
        uiModel.addAttribute("currentIndex", current);
        return "index";
    }

    /**
     * returns view containing all posts
     */
    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public String seePage(@PathVariable int pageNumber, Model uiModel) {
        System.out.println("/page/" + pageNumber);
        PageRequest newPgb = new PageRequest(pageNumber - 1, 5);
        Page<PostEntity> currentResults = postService.getAllFrontPosts(newPgb);
        uiModel.addAttribute("currentResults",currentResults);
        int totalPages = currentResults.getTotalPages();
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 5, currentResults.getTotalPages());
        String url = "/post/page";
        String leaddescription = "Your daily dose of noscope.";
        uiModel.addAttribute("leaddescription",leaddescription);
        uiModel.addAttribute("url", url);
        uiModel.addAttribute("totalPages",totalPages);
        uiModel.addAttribute("beginIndex", begin);
        uiModel.addAttribute("endIndex", end);
        uiModel.addAttribute("currentIndex", current);
        return "index";

    }


    @RequestMapping(value = "/full/{id}")
    public ModelAndView seePost( @PathVariable long id) {
        ModelAndView mav = new ModelAndView("post");
        mav.addObject("post",postService.getFull(id));
        return mav;
    }
}
