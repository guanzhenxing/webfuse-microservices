package cn.webfuse.demos.mybatis.upms.mapper;

import cn.webfuse.framework.data.mybatis.pageable.PageRowBounds;
import cn.webfuse.demos.mybatis.upms.model.Actions;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ActionsMapper {

    @Select("select * from actions")
    List<Actions> list(PageRowBounds pageRowBounds);


}