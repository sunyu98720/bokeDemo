package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author
 *格式转换器,兼容json和formdata
 * @param
 * @return
 * @time
 */
public class HttpRequestUtil {
    public static Map<String,String> commonHttpRequestParamConvert(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        try{
            Map<String,String[]> requestParams = request.getParameterMap();
            if(requestParams != null && !requestParams.isEmpty()){
                requestParams.forEach((key, value) -> params.put(key,value[0]));
            }else {
                StringBuilder paramSb = new StringBuilder();
                try{
                    String str = "";
                    BufferedReader br = request.getReader();
                    while((str = br.readLine()) != null){
                        paramSb.append(str);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                if(paramSb.length() > 0){
                    JSONObject paramJsonObject = JSON.parseObject(paramSb.toString());
                    if (paramJsonObject != null && !paramJsonObject.isEmpty()) {
                        paramJsonObject.forEach((key, value) -> params.put(key, String.valueOf(value)));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return params;
    }
}
