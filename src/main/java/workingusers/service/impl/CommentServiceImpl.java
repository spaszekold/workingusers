package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workingusers.entity.CommentEntity;
import workingusers.entity.PostEntity;
import workingusers.repository.CommentRepository;
import workingusers.repository.PostRepository;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.service.CommentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Comment> getCommentsFromPost(long postid) {
        System.out.println("commentserviceimpl @getcommentfrompost/" + postid);
        PostEntity p = postRepository.findOneById(postid);
        //System.out.println("post is: " + p);
        List<CommentEntity> temp = commentRepository.findAllByPostidOrderByCreatedDesc(p);
        //System.out.println("list of comment entities: " + temp);
        List<Comment> result = new ArrayList();



        for (CommentEntity c : temp) {
            long parentid = -1;
            if (c.getParent() != null)
                parentid = c.getParent().getId();
            result.add(new Comment(c.getContent(), c.getCreated(), c.getId(), c.getLilname(), c.getDepth(),parentid ));
        }


        return arrangeList(result);

    }

    @Override
    public List<Comment> addNew(long postid, CommentLittle comment) {
        System.out.println("comment service, adding comment to " + postid);
        PostEntity p = postRepository.findOneById(postid);
        CommentEntity parent = commentRepository.findOneById(comment.parentid);

        CommentEntity newComment = new CommentEntity(comment.content,new Date(), comment.lilname, p, parent);

        commentRepository.save(newComment);
        return getCommentsFromPost(postid);
    }


    private List<Comment> arrangeList(List<Comment> commentList) {
        long startTime = System.nanoTime();
        List<Comment> result = new ArrayList<Comment>();
        List<Comment> remainingComments = commentList;
        Iterator<Comment> commentIter = remainingComments.iterator();
        int maxDepth = 0;



        //calculate maximum depth of comments
        //and add no-parent comments to result list
        while (commentIter.hasNext()){
            Comment comment = commentIter.next();
            if (comment.getDepth() > maxDepth)
                maxDepth = comment.getDepth();
            if (comment.getDepth() == 0) {
                result.add(0,comment);
                commentIter.remove();
            }
        }


      //  System.out.println("drukuje liste result:");
       // System.out.println(result);
       // System.out.println("max depth wynosi:" + maxDepth);

        if (maxDepth != 0) {
            //while we havent reached maximum depth
            int currentDepth = 1;
            do {
                //get iterator from remainingComments
                commentIter = remainingComments.iterator();
                //iterate over entire comment list
                while (commentIter.hasNext()) {
                    //grab current comment
                    Comment comment = commentIter.next();
                    //if comment's depth equals currently looked-on depth
                    if (comment.getDepth() == currentDepth) {
                        //get parrent index
                        int parentindex = getIndexOf(result, comment.getParentid());
                       // System.out.println("Indeks ojca " + comment.id + " w liscie wynosi " + parentindex);
                        //put it in result after the parent
                        result.add(parentindex + 1, comment);
                        //remove from remainingComments list
                        commentIter.remove();
                    }

                }
            } while (currentDepth++ != maxDepth);
        }

        long endTime = System.nanoTime();
        System.out.println("ogarniecie komci zajelo " + (endTime - startTime)/1000000 + " ms");


        return result;
    }

    public int getIndexOf(List<Comment> list, long pid) {
        Iterator<Comment> iterator = list.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            Comment next = iterator.next();
            if (next.id == pid) {
            //    System.out.println("wychodzimy z loopa z indexem " + index);
                break;
            }
            index++;
        }



        return index;
    }



}
