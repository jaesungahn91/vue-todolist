package ese.example.todolist.dao;

import ese.example.todolist.dto.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
