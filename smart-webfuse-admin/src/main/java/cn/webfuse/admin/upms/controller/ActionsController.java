package cn.webfuse.admin.upms.controller;


import cn.webfuse.admin.upms.repository.ActionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/upms/actions")
public class ActionsController {

    @Autowired
    ActionsMapper actionsMapper;

    @GetMapping("/")
    public void test() {
        actionsMapper.selectAll();
    }

}

