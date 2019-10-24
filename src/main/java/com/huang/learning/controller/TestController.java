package com.huang.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/24 9:24
 */
public class TestController {
        @RequestMapping("/index")
    public String mapper(){
            return "my";
        }
}
