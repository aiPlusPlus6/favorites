package com.aiplusplus.favorites.common.customizeException.InfoInterface;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月19日 10:56
 * @package com.aiplusplus.favorites.common.customizeException.InfoInterface
 * @ClassName: ExceptionEnum
 * @Description: TODO(描述)
 */
@Getter
public enum ExceptionEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!"),
    OPERATION_FAILED("1001","操作失败!");


    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    ExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
