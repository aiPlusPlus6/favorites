package com.aiplusplus.favorites.common;

import com.aiplusplus.favorites.common.customizeException.InfoInterface.BaseErrorInfoInterface;
import com.aiplusplus.favorites.common.customizeException.InfoInterface.ExceptionEnum;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import lombok.Data;


/**
 * @author 李俊杰
 * {@code @date} 2024年02月19日 11:03
 * @package com.aiplusplus.favorites.common
 * @ClassName: R
 * @Description: TODO(描述)
 */
@Data
public class R<T> {
    /**
     * 业务错误码
     */
    private String code;
    /**
     * 结果集，使用泛型以支持不同类型的数据
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public R(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.msg = errorInfo.getResultMsg();
        this.data = null; // 由于构造函数不提供数据参数，这里初始化为null
    }

    public R() {
    }

    // 成功响应，不返回具体数据
    public static <T> R<T> ok() {
        return R.ok(null);
    }

    // 成功响应，返回具体类型的数据
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(ExceptionEnum.SUCCESS.getResultCode());
        r.setMsg(ExceptionEnum.SUCCESS.getResultMsg());
        r.setData(data);
        return r;
    }

    // 失败响应，使用错误信息接口
    public static <T> R<T> error(BaseErrorInfoInterface errorInfo) {
        R<T> rb = new R<>();
        rb.setCode(errorInfo.getResultCode());
        rb.setMsg(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }

    // 失败响应，指定错误码和消息
    public static <T> R<T> error(String code, String message) {
        R<T> rb = new R<>();
        rb.setCode(code);
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    // 失败响应，仅指定消息
    public static <T> R<T> error(String message) {
        R<T> rb = new R<>();
        rb.setCode("-1");
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString() {
        // 将对象转换为json字符串
        return "R{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
