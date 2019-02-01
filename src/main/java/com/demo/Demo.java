package com.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: demo
 * @Package: com.demo
 * @ClassName: Demo
 * @Author: 王尚
 * @Date: 2019/2/1 13:47
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        String param = "success";
        String s1 = JSON.toJSONString(param);
        String s = HttpJsonUtil.jsonPost("http://10.0.45.73:8869/medicaremgr/demo", s1, 18000);
        JSONObject jsonObject = JSONObject.parseObject(s);
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String key = next.getKey();
            Object value = next.getValue();
            System.out.println(key+":"+value);

        }

    }
}
