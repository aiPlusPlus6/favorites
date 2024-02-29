package com.aiplusplus.favorites.common.customizeException.InfoInterface;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月19日 10:53
 * @package com.aiplusplus.favorites.common.CustomizeException
 * @ClassName: BaseErrorInfoInterface
 * @Description: TODO(服务接口类)
 */
public interface BaseErrorInfoInterface {
    /**
     *  错误码
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     * @return
     */
    String getResultMsg();
}
