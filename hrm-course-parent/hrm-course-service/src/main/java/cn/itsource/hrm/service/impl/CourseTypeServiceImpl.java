package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.service.ICourseTypeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author hjz
 * @since 2019-12-26
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private RedisClient redisClient;

    private final String COURSE_TYPE = "hrm:course_type:treeData";


    @Override
    public List<CourseType> loadTreeData() {
        /*List<CourseType> courseTypes = loadTreeDataLoop();
        return courseTypes;*/

        //从redis中查询数据
        String courseTypesStr = redisClient.get(COURSE_TYPE);
        List<CourseType> list = null;

        //如果redis不存在，使用双重校验第一次redis不存在的时候查询数据库
        if(StringUtils.isEmpty(courseTypesStr)){
            //查询数据库，防止缓存穿透，大量请求同时查询数据库，同步代码块
            synchronized (CourseTypeServiceImpl.class){
                courseTypesStr = redisClient.get(COURSE_TYPE);
                if(StringUtils.isEmpty(courseTypesStr)){
                    //如果不存在，则查询数据库
                    list = loadTreeDataLoop();
                    //list集合转json字符串
                    String jsonStr = JSONObject.toJSONString(list);
                    //保存到redis中
                    redisClient.set(COURSE_TYPE, jsonStr);
                    return list;
                }
            }

        }

        //如果存在
        //json字符串转java集合
        list = JSONObject.parseArray(courseTypesStr, CourseType.class);
        System.out.println(list);
        return list;
    }

    /**
     * 循环方式
     * @return
     */
    public List<CourseType> loadTreeDataLoop(){
        //初始化一个集合存放一级类型
        List<CourseType> firstLevelTypes = new ArrayList<>();
        //查询数据库中的所有类型
        List<CourseType> courseTypes = baseMapper.selectList(null);

        //创建一个Map，将courseTypes数据存到map中，key使用id，value就是CourseType
        Map<Long,CourseType> map = new HashMap<>();
        for (CourseType courseType : courseTypes) {
            map.put(courseType.getId(), courseType);
        }

        //循环courseTypes，分配一级类型和非一级类型
        for (CourseType courseType : courseTypes) {
            if(courseType.getPid().longValue() == 0L){
                firstLevelTypes.add(courseType);
            }else{
                CourseType parent = map.get(courseType.getPid());
                if(parent!=null){
                    parent.getChildren().add(courseType);
                }
            }
        }

        return firstLevelTypes;
    }

    /**
     * 增删改同步操作
     */
    private void synOperate() {
        List<CourseType> list = loadTreeDataLoop();
        //list集合转json字符串
        String jsonStr = JSONObject.toJSONString(list);
        //保存到redis中
        redisClient.set(COURSE_TYPE, jsonStr);
    }


    @Override
    public boolean save(CourseType entity) {
        super.save(entity);
        synOperate();
        return true;
    }


    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        synOperate();
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        super.updateById(entity);
        synOperate();
        return true;
    }
}