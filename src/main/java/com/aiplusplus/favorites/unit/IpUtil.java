package com.aiplusplus.favorites.unit;



import com.jthinking.common.util.ip.IPInfo;
import com.jthinking.common.util.ip.IPInfoUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月22日 11:31
 * @package com.aiplusplus.favorites.unit
 * @ClassName: IpUtil
 * @Description: TODO(ip归属地查询)
 */
@Slf4j
public class IpUtil {

    public static String[] getIPRegion(HttpServletRequest request) {
        String ip = getIPAddress(request);
        log.info("ip:{}", ip);
        IPInfo ipInfo = IPInfoUtils.getIpInfo(ip);
        String address = ipInfo.getAddress();
        String[] strings = parseAddress(address);
        if(strings==null||strings.length<2){
            return null;
        }
        return strings;
    }


    public static String getIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static String[] parseAddress(String address) {
        // 定义正则表达式，用于匹配省、市、区
        String regex = "([^省]+省|[^自治区]+自治区|[^市]+市)([^市]+市|[^自治州]+自治州|[^地区]+地区|[^盟]+盟)?([^县]+县|[^区]+区|[^市]+市|[^旗]+旗)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);

        if (matcher.find()) {
            String province = matcher.group(1); // 省
            String city = matcher.group(2); // 市
            String district = matcher.group(3); // 区
            return new String[]{province, city, district};
        }
        return null; // 如果未匹配到，则返回null
    }
}