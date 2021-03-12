package ese.example.todolist.web;

import com.google.gson.Gson;
import ese.example.todolist.dao.ContentsDao;
import ese.example.todolist.dao.ContentsRepository;
import ese.example.todolist.dto.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "contents")
public class ContentsController {

    @Autowired
    ContentsRepository contentsRepository;

    @Autowired
    ContentsDao contentsDao;

    /**
     * contents page
     */
    @RequestMapping(value = "{category_id}")
    public String contents() {
        return "contents";
    }

    /**
     * contents getList
     */
    @RequestMapping(value = "{category_id}/getList",  method = {RequestMethod.GET})
    @ResponseBody
    public String getList(
            @PathVariable Long category_id
    ){
//        Contents contents = new Contents();
//        contents.setCategory_id(category_id);
        Gson gson = new Gson();
//        List<Contents> contents = contentsRepository.findAll();
//        List<Contents> contents = contentsDao.getList(category_id);
        String list = gson.toJson(contentsRepository.findAll());
        return list;
    }

    /**
     * contents insert
     */
    @RequestMapping(value = "{category_id}/insert",  method = {RequestMethod.POST})
    @ResponseBody
    public void insert(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(value = "category_id") Long category_id,
            @RequestParam(value = "contents_name", required = false, defaultValue = "") String contents_name
    ){
        Contents contents = new Contents();
        contents.setCategory_id(category_id);
        contents.setContents_nm(contents_name);
        contentsRepository.save(contents);
    }

    /**
     * contents update
     */
    @RequestMapping(value = "{category_id}/update",  method = {RequestMethod.POST})
    @ResponseBody
    public void update(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "contents_name", required = false, defaultValue = "") String contents_name,
            @RequestParam(value = "contents_id", required = false, defaultValue = "") Long contents_id,
            @PathVariable(value = "category_id") Long category_id
    ){
        Contents contents = new Contents();
        contents.setContents_nm(contents_name);
        contents.setContents_id(contents_id);
        contents.setCategory_id(category_id);
        contentsRepository.save(contents);
    }

    /**
     * contents delete
     */
    @DeleteMapping("{contents_id}/delete")
    @ResponseBody
    public void delete(@PathVariable Long contents_id) {
        Contents contents = new Contents();
        contents.setContents_id(contents_id);
        contentsRepository.delete(contents);
    }
}
