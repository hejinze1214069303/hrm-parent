package cn.itsource.hrm.client.impl;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.client.CourseEsClient;
import cn.itsource.hrm.document.CourseDoucment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2020/1/123:47
 * @Version v1.0
 */
@Component
public class CourseEsFallback implements CourseEsClient {

    @Override
    public AjaxResult createIndexes(List<CourseDoucment> courses) {
        return AjaxResult.me().setSuccess(false).setMessage("失败!");
    }

    @Override
    public AjaxResult deleteIndexes(List<Long> ids) {
        return AjaxResult.me().setSuccess(false).setMessage("失败!");
    }
}
