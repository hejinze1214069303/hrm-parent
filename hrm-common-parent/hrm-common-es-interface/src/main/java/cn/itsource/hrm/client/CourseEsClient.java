package cn.itsource.hrm.client;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.client.impl.CourseEsFallback;
import cn.itsource.hrm.document.CourseDoucment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2020/1/123:46
 * @Version v1.0
 */
@FeignClient(value = "ES-SERVICE",path = "/es",fallback = CourseEsFallback.class)
public interface CourseEsClient {

    @PostMapping("/create")
    AjaxResult createIndexes(@RequestBody List<CourseDoucment> courses);

    /**
     * 删除索引
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    AjaxResult deleteIndexes(@RequestBody List<Long> ids);

}
