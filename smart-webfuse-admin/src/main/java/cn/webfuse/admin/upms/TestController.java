package cn.webfuse.admin.upms;

import cn.webfuse.admin.upms.model.GroupRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/one")
    public GroupRole getOne() {


        GroupRole gr = new GroupRole();
        gr.setGroupId(1000L);
        gr.setId(1L);
        gr.setRoleId(200L);
        gr.setCreateTime(new Date());

        return gr;
    }
}
