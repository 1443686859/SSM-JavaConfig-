package com.huang.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/19 10:14
 */
@Controller
public class UserController {
    @RequestMapping("/")
    public String post(){
        return "index";
    }
    @RequestMapping("/index")
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response) {
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("my");
       modelAndView.addObject("nihao","jkjkkk");
       return modelAndView;
    }
}
