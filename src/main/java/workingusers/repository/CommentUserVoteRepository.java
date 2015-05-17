package workingusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import workingusers.entity.CommentEntity;
import workingusers.entity.CommentUserVote;
import workingusers.entity.CommentUserVoteId;
import workingusers.entity.UserEntity;

import java.util.List;

/**
 * Created by Tomek on 2015-05-16.
 */
public interface CommentUserVoteRepository extends JpaRepository<CommentUserVote, CommentUserVoteId> {


    List<CommentUserVote> findAllByCommentUserVoteIdUserAndPostid(UserEntity userEntity, long postid);

    CommentUserVote findOneByCommentUserVoteId(CommentUserVoteId commentUserVoteId);
}
