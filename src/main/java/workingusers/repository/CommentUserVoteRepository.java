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


   // @Query(value = "SELECT * FROM COMMENT_USER_VOTES WHERE commentUserVoteId.commen", nativeQuery = true)
    List<CommentUserVote> findOneByCommentUserVoteId(CommentUserVoteId commentUserVoteId);
}
