package com.aiplusplus.favorites.unit;


import com.aiplusplus.favorites.common.customizeException.BizException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;


import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月22日 11:31
 * @package com.aiplusplus.favorites.unit
 * @ClassName: IpUtil
 * @Description: TODO(ip归属地查询)
 */
@Slf4j
public class IpUtil {

    private static final String DB_PATH = "src/main/resources/ip2region.xdb";

    private static final ThreadLocal<Searcher> searcherThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return Searcher.newWithFileOnly(DB_PATH);
        } catch (Exception e) {
            log.error("初始化 IP 归属地查询失败: {}", e.getMessage());
            return null;
        }
    });


    public static String getIPRegion(HttpServletRequest request) {
        String ip = getIPAddress(request);
        //校验ip是否是v6
        if (ip !=null){
            if (ip.contains(":")){
                return "未知ip";
            }
        }
        Searcher searcher = searcherThreadLocal.get();
        if (searcher == null) {
            log.error("IP 归属地查询失败，返回空");
            return "未知ip";
        }
        try {
            long startTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
            log.info("IP: {}, Region: {}, IO Count: {}, Took: {} μs", ip, region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            log.error("IP: {} 获取 IP 归属地错误，错误原因: {}", ip, e.getMessage());
            return "未知ip";
        } finally {
            closeSearcher();
        }
    }


    private static String getIPAddress(HttpServletRequest request) {
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

    public static void closeSearcher() {
        try {
            Searcher searcher = searcherThreadLocal.get();
            if (Objects.nonNull(searcher)) {
                searcher.close();
                searcherThreadLocal.remove();
            }
        } catch (Exception e) {
            log.error("关闭异常", e);
        }
    }
}