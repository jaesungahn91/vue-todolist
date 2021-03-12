package ese.example.todolist.web;

import com.google.gson.Gson;
import ese.example.todolist.dao.CategoryRepository;
import ese.example.todolist.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * category page
     */
    @RequestMapping(value = "main")
    public String category() {
        return "index";
    }

    /**
     * category getList
     */
    @RequestMapping(value = "getList",  method = {RequestMethod.GET})
    @ResponseBody
    public String getList(){
        Gson gson = new Gson();
        String list = gson.toJson(categoryRepository.findAll());
        return list;
    }

    /**
     * category insert
     */
    @RequestMapping(value = "insert",  method = {RequestMethod.POST})
    @ResponseBody
    public void insert(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "category_name", required = false, defaultValue = "") String category_name
            ){
        Category category = new Category();
        category.setCategory_nm(category_name);
        categoryRepository.save(category);
    }

    /**
     * category update
     */
//    @RequestMapping(value = "update",  method = {RequestMethod.POST})
    @PutMapping(value = "update")
    @ResponseBody
    public void update(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "category_name", required = false, defaultValue = "") String category_name,
            @RequestParam(value = "category_id", required = false, defaultValue = "") Long category_id
    ){
        Category category = new Category();
        category.setCategory_nm(category_name);
        category.setCategory_id(category_id);
        categoryRepository.save(category);
    }

    /**
     * category delete
     */
    @DeleteMapping("delete/{category_id}")
    @ResponseBody
    public void delete(@PathVariable Long category_id) {
        Category category = new Category();
        category.setCategory_id(category_id);
        categoryRepository.delete(category);
    }

}
