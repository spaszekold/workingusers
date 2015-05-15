package workingusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import workingusers.entity.TagEntity;
import workingusers.rest.Tag;

import java.util.List;

/**
 * Created by Tomek on 2015-05-01.
 */
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    public TagEntity findOneById(long tagid);
    public TagEntity findOneByTagname(String tagname);


    @Query(value = "SELECT * FROM tags WHERE tagname LIKE %:name%", nativeQuery = true)
    public List<TagEntity> findAllByTagnameLike(@Param("name")String name);
}
