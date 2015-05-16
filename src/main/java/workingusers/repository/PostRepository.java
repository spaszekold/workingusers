package workingusers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import workingusers.entity.PostEntity;
import workingusers.entity.TagEntity;
import workingusers.entity.UserEntity;

import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    public Page<PostEntity> findAllByOrderByCreatedDesc(Pageable pageable);

    public Page<PostEntity> findAllByTagsOrderByCreatedDesc(TagEntity tagEntity, Pageable pageable);

    public Page<PostEntity> findAllByUseridOrderByCreatedDesc(UserEntity userEntity, Pageable pageable);

    public long countByTags(TagEntity tagEntity);

   // public List<PostEntity> findAllByOrderByCreatedDesc();
    public PostEntity findOneByPostid(long id);
}
