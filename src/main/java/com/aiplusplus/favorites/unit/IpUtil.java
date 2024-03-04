package com.aiplusplus.favorites.unit;



import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.vo.TencentIPVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
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
@Component
public class IpUtil {
    @Value("${tencent.http}")
    private  String http;
    @Value("${tencent.location}")
    private String url;
    @Value("${tencent.key}")
    private String key;
    @Value("${tencent.sk}")
    private String sk;
    public  String[] getIPRegion(HttpServletRequest request) {
        String ip = getIPAddress(request);
        log.info("ip:{}", ip);
        //校验是否是局域网以及本地ip
        if(isLocalOrLanIP(ip)){
            throw new BizException("局域网ip不支持");
        }
        String uriString = url+"?ip="+ip+"&key="+key+sk;
        String sig = md5(uriString);
        Map<String, String> map = new HashMap<>();
        map.put("ip", ip);
        map.put("key", key);
        map.put("sig", sig);
        TencentIPVO result = HttpUtil.getClass(http + url, map, null, TencentIPVO.class, "result");
        if(result.getAd_info().getCity()==null||result.getAd_info().getCity().equals("")) {
            throw new BizException("未知信息");
        }
        return new String[]{result.getAd_info().getProvince(), result.getAd_info().getCity(), result.getAd_info().getDistrict()};
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

    //md5小写32位加密
    public  String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte b : byteDigest) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isLocalOrLanIP(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);

            // 检查IPv4和IPv6的本地回环地址
            if (address.isLoopbackAddress()) {
                return true;
            }

            // IPv4地址检查
            if (address.getAddress().length == 4) {
                byte[] ipBytes = address.getAddress();

                if ((ipBytes[0] & 0xFF) == 10) { // A类私有地址
                    return true;
                }
                if ((ipBytes[0] & 0xFF) == 172 && (ipBytes[1] & 0xF0) == 16) { // B类私有地址
                    return true;
                }
                if ((ipBytes[0] & 0xFF) == 192 && (ipBytes[1] & 0xFF) == 168) { // C类私有地址
                    return true;
                }
            } else if (address.getAddress().length == 16) { // IPv6地址检查
                byte[] ipBytes = address.getAddress();

                // 检查本地链接地址 (fe80::/10)
                if ((ipBytes[0] & 0xFF) == 0xfe && (ipBytes[1] & 0xc0) == 0x80) {
                    return true;
                }

                // 检查唯一本地地址 (ULA) (fc00::/7)
                if ((ipBytes[0] & 0xFE) == 0xfc) {
                    return true;
                }
            }

            // 不是本地或局域网地址
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}