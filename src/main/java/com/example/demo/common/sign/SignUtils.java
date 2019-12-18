package com.example.demo.common.sign;

import org.h2.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Author
 *
 * @param
 * @return
 * @time
 * 签名规则:
 * 1.时间戳,token,随机串
 * 2.全部大写
 * 3.key字典排序
 */
public class SignUtils {
    public String getTimestamp(){
        /**
        *@explain 生成时间戳
        *Author
        *@return java.lang.String
        *@time 2019/11/11 14:01
        */
        long timestanpLong = System.currentTimeMillis();
        String timestampStr = String.valueOf(timestanpLong);
        return timestampStr;
    }
    public String getNonceStr(int length){
        /**
        *@explain 生成随机字符串
        *Author
        *@param length
        *@return java.lang.String
        *@time 2019/11/11 14:02
        */
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int numbers = random.nextInt(62);
            randomStr.append(str.charAt(numbers));
        }
        return randomStr.toString();
    }
    public String createSign(Map<String,String> params, String privateKey){
        StringBuilder sb = new StringBuilder();
        Map<String,String> sortParams = new TreeMap<String,String>(params);
        for(Map.Entry<String,String> entry : sortParams.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().trim();
            if(!StringUtils.isNullOrEmpty(value)){
                sb.append("&").append(key).append("=").append(value);
            }
        }
        String stringA = sb.toString().replaceFirst("&", "");
        return stringA;
    }
}
