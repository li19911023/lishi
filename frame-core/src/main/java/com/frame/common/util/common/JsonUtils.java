package com.frame.common.util.common;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtils {

    private static final ObjectMapper om = new ObjectMapper();
	
	private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

	/**
	 * 
	 * [对象装json] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param object
	 * @return <br>
	 */
	public static String getJSONString(Object object) {
		String jsonString = null;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());// 注册日期处理器
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {// 如果是集合则当成集合数组处理
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			} else {// 如果是对象则如此处理
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

    /**
     * json串转换为map
     *
     * @param msg
     * @return
     * @throws IOException
     */
    public static Map<String, String> getMapFromJsonString(String msg)
            throws Exception {
        return om.readValue(msg, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * 转成map的
     * 
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (StringUtils.isNotEmpty(gsonString)) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
    
    /**
     * 
     * [把字符串的json装换成map] <br> 
     *  
     * @author [li.qiong]<br>
     * @param json
     * @return <br>
     */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json) {
       if(StringUtils.isNotEmpty(json)) {
    	   return JSON.parseObject(json, Map.class);
       }
       return null;
    }

    /**
     * 
     * [把对象转换成json数据，可以缺失字段转json] <br> 
     *  
     * @author [li.qiong]<br>
     * @param obj
     * @return <br>
     */
    public static String obj2Json(Object obj) {
       if(obj != null) {
    	   return JSON.toJSONString(obj);
       }
       return null;
    }
    
    /**
     * 
     * [把字符串装json] <br> 
     *  
     * @author [li.qiong]<br>
     * @param str
     * @return <br>
     * @throws org.json.JSONException 
     */
    public static JSONObject getJSONByString(String str) {
        if(StringUtils.isNotEmpty(str)) {
        	JSONObject json = JSONObject.fromObject(str);
        	return json;
        }
		return null;
    }
    
    /**
     * 
     * [把JSONObject转换成对象] <br> 
     *  
     * @author [li.qiong]<br>
     * @param jSONObject
     * @return <br>
     */
    public static <T> T getObjectByJSON(JSONObject jSONObject, Class<T> cls) {
    	if( jSONObject != null ) {
    		T t = JSON.parseObject(jSONObject.toString(), cls);
    		return t;
    	}
    	return null;
    }
    
    /**
     * 
     * [将map转换成对象] <br> 
     *  
     * @author [li.qiong]<br>
     * @param map
     * @param cls
     * @return <br>
     */
	public static <T> T getObjByMap(Map<String,Object> map , Class<T> cls) {
    	if(map != null && !map.toString().equals("{}")) {
    		return com.alibaba.fastjson.JSONObject.parseObject(com.alibaba.fastjson.JSONObject.toJSONString(map), cls);
    	}
		return null;
    }
	
	/**
     * List<T> 转 json
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }
	
}
