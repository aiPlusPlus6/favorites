package com.aiplusplus.favorites.doman.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月04日 11:06
 * @package com.aiplusplus.favorites.doman.vo
 * @ClassName: TencentIPVO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "腾讯IP定位返回值")
public class TencentIPVO {

    /**
     * ip : 2409:8907:44c:7c2::1
     * location : {"lat":38.0061,"lng":114.53122}
     * ad_info : {"nation":"中国","province":"河北省","city":"石家庄市","district":"裕华区","adcode":130108,"nation_code":156}
     */
    @Schema(name = "ip", description = "ip地址")
    private String ip;
    @Schema(name = "location", description = "经纬度")
    private LocationBean location;
    @Schema(name = "ad_info", description = "地理信息")
    private AdInfoBean ad_info;


    @Data
    public static class LocationBean {
        /**
         * lat : 38.0061
         * lng : 114.53122
         */

        @Schema(name = "lat", description = "纬度")
        private double lat;
        @Schema(name = "lng", description = "经度")
        private double lng;
    }
    @Data

    public static class AdInfoBean {
        /**
         * nation : 中国
         * province : 河北省
         * city : 石家庄市
         * district : 裕华区
         * adcode : 130108
         * nation_code : 156
         */

        @Schema(name = "nation", description = "国家")
        private String nation;
        @Schema(name = "province", description = "省")
        private String province;
        @Schema(name = "city", description = "市")
        private String city;
        @Schema(name = "district", description = "区")
        private String district;
        @Schema(name = "adcode", description = "行政区划代码")
        private Integer adcode;
        @Schema(name = "nation_code", description = "国家代码")
        private Integer nation_code;
    }
}
