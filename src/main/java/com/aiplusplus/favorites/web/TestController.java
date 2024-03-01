package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.unit.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月27日 14:34
 * @package com.aiplusplus.favorites.web
 * @ClassName: TestController
 * @Description: TODO(测试)
 */
@Controller
@RequestMapping("/test")
@ResponseBody
public class TestController {

    @GetMapping("/test")
    public R<String> test(HttpServletRequest request) {
        return R.ok(IpUtil.getIPRegion(request));
    }

    @GetMapping("/test2")
    public R<String> test2() {
        return R.ok("test2");
    }
}
