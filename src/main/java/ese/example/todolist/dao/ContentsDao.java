package ese.example.todolist.dao;

import ese.example.todolist.dto.Contents;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "ContentsDao")
public class ContentsDao {

    @PersistenceContext
    EntityManager em;

    public List<Contents> getList(Long id){

        Long category_id = id;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT c FROM todolist.contents AS c ");
        //sb.append(" SELECT t FROM DvcInfo AS t");
        sb.append(" WHERE category_id = :category_id ");

        TypedQuery<Contents> query = em.createQuery(sb.toString(), Contents.class);

        query.setParameter("category_id", category_id);
        List<Contents> list = query.getResultList();
        return list;
    }
}
