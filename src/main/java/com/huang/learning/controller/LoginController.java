package com.huang.learning.controller;


import com.alibaba.fastjson.JSONObject;
import com.huang.learning.service.LoginService;
import com.huang.learning.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
        @Autowired
        private LoginService loginService;
        @PostMapping("/login")
        public JSONObject authLogin(@RequestBody JSONObject requestJson) {
//                SecurityUtils.getSubject().login(token);
                //未进行输入处理参数
                //CommonUtil.hasAllRequired(requestJson, "username,password");
                return loginService.authLogin(requestJson);
        }
}
