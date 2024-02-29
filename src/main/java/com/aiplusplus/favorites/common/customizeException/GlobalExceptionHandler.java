package com.aiplusplus.favorites.common.customizeException;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.common.customizeException.InfoInterface.ExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

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
    public R<String> bizExceptionHandler(HttpServletRequest req, BizException e){
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
    public R<String> exceptionHandler(HttpServletRequest req, NullPointerException e){
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
    public R<String> exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！请联系李俊杰,原因是:",e);
        return R.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理参数校验异常
     * @param req 请求
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<String> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        log.error("参数校验异常！原因是:",e);
        // 获取所有的字段错误，然后转换成自定义的错误消息格式返回
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return R.error("1001", errorMessage);
    }
}
