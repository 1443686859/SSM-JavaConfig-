package com.huang.learning.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.huang.learning.common.Status;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/23 8:29
 */
public class JSONUtil {

    public  static JSONObject successJson(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code", Status.Success.getCode());
        jsonObject.put("msg",Status.Success.getMessage());
        jsonObject.put("info",new JSONObject());
        return jsonObject;
    }
    /**
     * 返回成功的json数据包含数据info
     * @param info
     * @return
     */
    public static JSONObject successJson(Object info){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code", Status.Success.getCode());
            jsonObject.put("msg",Status.Success.getMessage());
            jsonObject.put("info",info);
            return jsonObject;
    }

    /**
     * 使用枚举类error 参数添加错误状态json
     * @param status
     * @return
     */
    public  static JSONObject errorJson(Status status){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",status.getCode());
        jsonObject.put("msg",status.getMessage());
        jsonObject.put("info",new JSONObject());
        return  jsonObject;
    }

    /**
     * 转换request到Json格式
     * @param request
     * @return
     */
    public  static JSONObject convertRequestToJson(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Enumeration parameters=request.getParameterNames();
        while(parameters.hasMoreElements()){
            String param= (String) parameters.nextElement();
            String[] pv=request.getParameterValues(param);
            //拼接字符串
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<pv.length;i++){
                if(pv[i].length()>0){
                        if(i>0){
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(pv[i]);
                }
            }
            jsonObject.put(param,stringBuilder.toString());
        }
        return jsonObject;
    }



}
