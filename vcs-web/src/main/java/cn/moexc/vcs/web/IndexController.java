package cn.moexc.vcs.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {

    @RequestMapping
    public R index(){
        return R.success("version 1.0");
    }
}
