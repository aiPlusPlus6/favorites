package com.aiplusplus.favorites.common.customizeException;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.common.customizeException.InfoInterface.ExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月19日 11:56
 * @package com.aiplusplus.favorites.common.customizeException
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(异常处理)
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req 请求
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public R bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return R.error(e.getErrorCode(),e.getErrorMsg());
    }
    /**
     * 处理空指针的异常
     * @param req 请求
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public R exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return R.error(ExceptionEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     * @param req 请求
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public R exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！请联系李俊杰,原因是:",e);
        return R.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
    //拦截Security未登录异常

}
