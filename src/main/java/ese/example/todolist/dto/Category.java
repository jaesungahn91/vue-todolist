package ese.example.todolist.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category"/*, schema = "todolist"*/)
@SequenceGenerator(
        name="seq_category_gen",
        sequenceName="seq_category",
        initialValue=1,
        allocationSize=1
)
public class Category {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_category_gen"
    )
    private Long category_id;
    private String category_nm;
    private String category_del = "N";

}
