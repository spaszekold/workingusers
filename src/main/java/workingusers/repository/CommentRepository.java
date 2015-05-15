package workingusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import workingusers.entity.CommentEntity;
import workingusers.entity.PostEntity;

import java.util.List;

/**
 * Created by Tomek on 2015-05-01.
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    public CommentEntity findOneById(long id);

    public List<CommentEntity> findAllByPostidOrderByCreatedDesc(PostEntity postid);
}
