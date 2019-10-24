package com.huang.learning.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/23 10:18
 */
public class CommonUtil {
    /**
     * 获取页数
     * @param pageRow
     * @param totalCount
     * @return
     */
    public static int getPageCounts(int pageRow,int totalCount){
        if(totalCount<pageRow){
            return 1;
        }
        return totalCount%pageRow>0?
                totalCount/pageRow +1:
                totalCount/pageRow;
    }
    /**
     * 修改参数使之合理
     * @param jsonObject
     * @param defaultPageRow
     */
    public static void fillPageParam(final JSONObject jsonObject , int defaultPageRow){
        int pageNum=jsonObject.getIntValue("pageNum");
        pageNum=pageNum==0?1:pageNum;
        int pageRow=jsonObject.getIntValue("pageRow");
        pageRow=pageRow==0?defaultPageRow:pageNum;
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("pageRow",pageRow);
        jsonObject.put("offset",(pageNum-1)*pageRow);

    }
    public static void fillPageParam(final JSONObject jsonObject){
        fillPageParam(jsonObject,10);
    }
    /**
     * 查询分页结果后的封装工具方法
     *
     * @param list 查询分页对象list
     */
    public static JSONObject successPage(List<JSONObject> list) {
        JSONObject result = JSONUtil.successJson();
        JSONObject info = new JSONObject();
        info.put("list", list);
        result.put("info", info);
        return result;
    }
    public static JSONObject successPage(final JSONObject requestJson, List<JSONObject> list, int totalCount) {
        int pageRow = requestJson.getIntValue("pageRow");
        int totalPage = getPageCounts(pageRow, totalCount);
        JSONObject result = JSONUtil.successJson();
        JSONObject info = new JSONObject();
        info.put("list", list);
        info.put("totalCount", totalCount);
        info.put("totalPage", totalPage);
        result.put("info", info);
        return result;
    }

}
