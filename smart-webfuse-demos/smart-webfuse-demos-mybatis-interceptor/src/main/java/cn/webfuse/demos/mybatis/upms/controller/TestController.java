package cn.webfuse.demos.mybatis.upms.controller;

import cn.webfuse.framework.data.mybatis.plugin.pageable.PageRowBounds;
import cn.webfuse.demos.mybatis.upms.mapper.ActionsMapper;
import cn.webfuse.demos.mybatis.upms.model.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    ActionsMapper actionsMapper;

    @GetMapping("/actions")
    public List<Actions> listActions() {
        return actionsMapper.list(new PageRowBounds(0, 1));
    }

}
