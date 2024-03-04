package com.aiplusplus.favorites.unit;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:11
 * @package com.aiplusplus.favorites.unit
 * @ClassName: HttpUtil
 * @Description: TODO(http工具类)
 */
public class HttpUtil {
    private final static RestTemplate restTemplate = new RestTemplate();
    private final static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * get请求获取map对象
     * @param url 请求地址如果带有restful风格参数，需要在url中加上{参数名}，然后在pathMap中加上对应的参数例如：url = "http://localhost:8080/{id}/test"
     * @param paramMap 请求参数 例如：key参数名 value参数值
     * @param pathMap restful风格参数 例如：key参数名 value参数值
     * @return 接口返回值
     */
    public static Map<String,Object> getMapObject(String url, Map<String,String> paramMap,Map<String,String> pathMap) {
        if(pathMap != null) {
            //将url里的{参数名}替换成对应的参数值
            for (Map.Entry<String, String> entry : pathMap.entrySet()) {
                url = url.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        if(paramMap != null){
            for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
                url = UriComponentsBuilder.fromHttpUrl(url).queryParam(stringStringEntry.getKey(), stringStringEntry.getValue()).toUriString();
            }
        }
        Map<String,Object> forObject = restTemplate.getForObject(url, Map.class);
        return forObject;
    }

    /**
     * get请求获取指定类型的对象(单个)
     * @param url 请求地址如果带有restful风格参数，需要在url中加上{参数名}，然后在pathMap中加上对应的参数例如：url = "http://localhost:8080/{id}/test"
     * @param paramMap 请求参数 例如：key参数名 value参数值
     * @param pathMap restful风格参数 例如：key参数名 value参数值
     * @param clazz 返回值类型
     * @param data 返回值中的data字段名
     * @return 接口返回值
     * @param <T> 返回值类型
     */
    public static <T> T getClass(String url, Map<String,String> paramMap,Map<String,String> pathMap,Class<T> clazz,String data){
        Map<String, Object> mapObject = getMapObject(url, paramMap, pathMap);
        if (data == null){
            return objectMapper.convertValue(mapObject,clazz);
        }
        Map<String, Object> o = (Map<String, Object>) mapObject.get(data);
        return objectMapper.convertValue(o,clazz);
    }

    /**
     * get请求获取指定类型的对象(list)
     * @param url 请求地址如果带有restful风格参数，需要在url中加上{参数名}，然后在pathMap中加上对应的参数例如：url = "http://localhost:8080/{id}/test"
     * @param paramMap 请求参数 例如：key参数名 value参数值
     * @param pathMap restful风格参数 例如：key参数名 value参数值
     * @param typeReference 返回值类型
     * @param data 返回值中的data字段名
     * @return 接口返回值
     * @param <T> 返回值类型
     */
    public static <T> T getClassList(String url, Map<String,String> paramMap, Map<String,String> pathMap, TypeReference<T> typeReference, String data){
        Map<String, Object> mapObject = getMapObject(url, paramMap, pathMap);
        Object o = mapObject.get(data);
        return objectMapper.convertValue(o,typeReference);
    }

}
