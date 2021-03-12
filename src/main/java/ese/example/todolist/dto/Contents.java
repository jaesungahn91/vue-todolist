package ese.example.todolist.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contents"/*, schema = "todolist"*/)
@SequenceGenerator(
        name="seq_contents_gen",
        sequenceName="seq_contents",
        initialValue=1,
        allocationSize=1
)
public class Contents {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_contents_gen"
    )
    private Long contents_id;
    private Long category_id;
    private String contents_nm;
    private String contents_del;

}
