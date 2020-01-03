package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.document.CourseDoucment;
import cn.itsource.hrm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2020/1/123:39
 * @Version v1.0
 */
@RestController
@RequestMapping("/es")
public class CourseEsController {

    @Autowired
    private CourseRepository repository;

    /**
     * 创建索引
     * @param courses
     * @return
     */
    @PostMapping("/create")
    public AjaxResult createIndexes(@RequestBody List<CourseDoucment> courses){
        try {
            repository.saveAll(courses);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败!");
        }
    }

    /**
     * 删除索引
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public AjaxResult deleteIndexes(@RequestBody List<Long> ids){
        try {
            //如果讲究效率，将List<Long>转成 List<CourseDocument> 调用deleteAll方法
            for (Long id : ids) {
                repository.deleteById(id);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败!");
        }
    }

}
