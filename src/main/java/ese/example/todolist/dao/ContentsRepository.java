package ese.example.todolist.dao;

import ese.example.todolist.dto.Contents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {
    @Query(value = "select c from contents c where c.category_id = ?1", nativeQuery = true)
    List<Contents> findAllByCategoryId(Long category_id);
}
