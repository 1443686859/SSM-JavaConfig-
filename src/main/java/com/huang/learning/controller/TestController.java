package com.huang.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/24 9:24
 */
@Controller
public class TestController {
        @RequestMapping(value = "/login",method = RequestMethod.GET)
        public String login(){
            return "my";
        }
        @RequestMapping(value = "/index",method = RequestMethod.GET,produces = "text/html")
        @ResponseBody
        public void mapper(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Date now = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
            //System.out.println(request.getSession().getServletContext().getRealPath("/"));
            System.out.println("TestController.mapper  "+ft.format(now));

            System.out.println("--------------");
             response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>"); ;
        }

}
