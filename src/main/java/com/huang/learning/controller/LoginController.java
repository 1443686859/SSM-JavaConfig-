package com.huang.learning.controller;


import com.alibaba.fastjson.JSONObject;
import com.huang.learning.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class LoginController {
        @Autowired
        private LoginService loginService;
        @PostMapping(value = "/login",consumes = "application/json")
        public JSONObject authLogin(@RequestBody JSONObject requestJson) {
            //SecurityUtils.getSubject().login(token);
                //未进行输入处理参数
                //CommonUtil.hasAllRequired(requestJson, "username,password");
            SecurityUtils.getSubject().login(new UsernamePasswordToken((String)requestJson.get("username"),(String)requestJson.get("password")));
            //return loginService.authLogin(requestJson);
            return requestJson;
        }
}
