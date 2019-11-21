package com.huang.learning.config.JWT;

import com.huang.learning.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author hky
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {


//    @Autowired
    //此处不能自动加载，因为这个文件快于主体加载
//   private RedisClient redis=new RedisClient();


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("is access allowed");
        String token=getRequestToken((HttpServletRequest)request);
        if(token==null) return  false;
        if(token=="") throw new Exception("authorization字段为空");
        if(JwtUtil.verify(token)){
            return true;
        }
        //未验证过期时间
        return false;

    }
    private String getRequestToken(HttpServletRequest request){
        String token=request.getHeader("authorization");
        if (token==null||token==""){
                token=request.getParameter("authorization");
        }
        return token;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        //获取参数
        if (JwtUtil.verify(jwt)) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
            try {
                //委托realm进行登录认证
                getSubject(request, response).login(usernamePasswordToken);
                return true;
            }catch (Exception e) {
                return false;
            }
        }
        redirectToLogin(servletRequest,response);
        return false;
    }
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        log.info("redirectToLogin");
        WebUtils.issueRedirect(request, response, "/login");
    }
}
