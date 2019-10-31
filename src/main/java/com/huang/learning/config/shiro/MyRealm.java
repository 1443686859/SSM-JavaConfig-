package com.huang.learning.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.huang.learning.service.LoginService;
import com.huang.learning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/22 9:21
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session;
        session = SecurityUtils.getSubject().getSession();
        JSONObject jsonObject= (JSONObject) session.getAttribute("userPermission");
        log.info("用户权限:"+jsonObject);
        log.info("权限列表："+jsonObject.get("permissionList"));
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions((Collection<String>) jsonObject.get("permissionList"));

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName=(String)token.getPrincipal();
        String password=new String((char[])token.getCredentials());
        JSONObject jsonObject=loginService.getUser(loginName,password);
        if(jsonObject==null){
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                jsonObject.getString("username"),
                jsonObject.getString("password"),getName());
        //return null;
        jsonObject.remove("password");
        SecurityUtils.getSubject().getSession().setAttribute("userInfo",jsonObject);
        return authenticationInfo;
    }
}
