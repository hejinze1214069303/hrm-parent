package cn.itsource.hrm.repository;

import cn.itsource.hrm.document.CourseDoucment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2020/1/123:43
 * @Version v1.0
 */
@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDoucment,Long> {
}