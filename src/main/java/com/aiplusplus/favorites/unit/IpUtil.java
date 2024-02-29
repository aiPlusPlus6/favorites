package com.aiplusplus.favorites.unit;

import com.ejlchina.okhttps.OkHttps;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.InetAddress;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月22日 11:31
 * @package com.aiplusplus.favorites.unit
 * @ClassName: IpUtil
 * @Description: TODO(描述)
 */
public class IpUtil {

    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private static final String localIp = "127.0.0.1";

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ipAddress.indexOf(",") != -1) {
                    ipAddress = ipAddress.split(",")[0];
                    logger.info("多次反向代理后 ip: " + ipAddress);
                }
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
                logger.info("Proxy-Client-IP ip: " + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
                logger.info("WL-Proxy-Client-IP ip: " + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
                logger.info("HTTP_CLIENT_IP ip: " + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
                logger.info("HTTP_X_FORWARDED_FOR ip: " + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("X-Real-IP");
                logger.info("X-Real-IP ip: " + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (localIp.equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                    logger.info("根据网卡取本机配置 ip: " + ipAddress);
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                    logger.info("多次反向代理后 ip: " + ipAddress);
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        logger.info("IP地址信息：" + ipAddress);
        return "0:0:0:0:0:0:0:1".equals(ipAddress) ? localIp : ipAddress;
    }

    /**
     * 获取访问设备
     *
     * @param request 请求
     * @return {@link UserAgent} 访问设备
     */
    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

    /**
     * 获取浏览器名称
     *
     * @param request
     * @return
     */
    public static String getBrowserName(HttpServletRequest request) {
        String name = getUserAgent(request).getBrowser().getName();
        return name.equals("UNKNOWN") ? name : "非浏览器";
    }

    /**
     * 获取系统名称
     *
     * @param request
     * @return
     */
    public static String getSystemName(HttpServletRequest request) {
        String name = getUserAgent(request).getOperatingSystem().getName();
        return name.equals("UNKNOWN") ? name : "未知";
    }

    /**
     * 根据ip获取地址
     *
     * @param ip
     * @return
     */
    public static String analyzeIp(String ip) {
        try {
            // 如果是127.0.0.1
            if (ip.equals(localIp)) {
                return "局域网（未知来源）";
            }
            // 请求
            String url = "http://whois.pconline.com.cn/ipJson.jsp?json=true&ip=" + ip;
            String body = OkHttps.sync(url).get().getBody().toString();
            SoMap so = SoMap.getSoMap().setJsonString(body);
            logger.info("IP地址详情信息 = " + so);
            String addr = so.getString("addr");
            if (addr == null) {
                return "未知地址";
            }
            if (addr.contains("本机地址") || addr.contains("局域网")) {
                return "局域网（未知来源）";
            }
            // 正常返回
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
            return "未知地址";
        }
    }
}