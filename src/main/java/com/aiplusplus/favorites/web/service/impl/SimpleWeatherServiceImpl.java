package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.common.prefix.SimpleWeatherPrefix;
import com.aiplusplus.favorites.doman.dto.simple.SimpleCityReceiveDTO;
import com.aiplusplus.favorites.doman.entity.simple.SimpleCity;
import com.aiplusplus.favorites.mapper.SimpleCityMapper;
import com.aiplusplus.favorites.unit.HttpUtil;
import com.aiplusplus.favorites.unit.IpUtil;
import com.aiplusplus.favorites.web.service.SimpleCityService;
import com.aiplusplus.favorites.web.service.SimpleWeatherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:52
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: SimpleWeatherServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class SimpleWeatherServiceImpl implements SimpleWeatherService {
    private final SimpleCityService simpleCityService;
    private final SimpleWeatherPrefix simpleWeatherPrefix;
    private final SimpleCityMapper simpleCityMapper;

    //定时拉取城市列表每天凌晨0点
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void pullCityList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("key", simpleWeatherPrefix.getApikey());
        List<SimpleCityReceiveDTO> data = HttpUtil.getClassList(simpleWeatherPrefix.getCitylist(), mapParam, null, new TypeReference<List<SimpleCityReceiveDTO>>() {
        }, "result");
        List<SimpleCity> list = simpleCityService.list();
        Map<Integer, SimpleCity> simpleCityMap = list.stream().collect(Collectors.toMap(SimpleCity::getSimpleCityId, Function.identity()));
        Set<Integer> simpleCityIds = simpleCityMap.keySet();
        //新增城市
        List<SimpleCity> simpleCitieAdds = new ArrayList<>();
        //修改城市
        List<SimpleCity> simpleCitieUpdates = new ArrayList<>();
        for (SimpleCityReceiveDTO datum : data) {
            SimpleCity simpleCity = simpleCityMap.get(datum.getId());
            if (simpleCity == null) {
                simpleCity = new SimpleCity();
                simpleCity.setSimpleCityId(datum.getId());
                simpleCity.setProvince(datum.getProvince());
                simpleCity.setCity(datum.getCity());
                simpleCity.setDistrict(datum.getDistrict());
                simpleCity.setDeleted(0);
                simpleCitieAdds.add(simpleCity);
            } else {
                simpleCityIds.remove(datum.getId());
                simpleCity.setProvince(datum.getProvince());
                simpleCity.setCity(datum.getCity());
                simpleCity.setDistrict(datum.getDistrict());
                simpleCitieUpdates.add(simpleCity);
            }
        }
        if (!simpleCityIds.isEmpty()) {
            boolean b = simpleCityService.remove(new LambdaQueryWrapper<SimpleCity>().in(SimpleCity::getSimpleCityId, simpleCityIds));
            if (!b) {
                log.error("删除城市失败");
                throw new BizException("更新城市失败");
            }
        }
        if (!simpleCitieAdds.isEmpty()) {
            boolean b = simpleCityService.saveBatch(simpleCitieAdds);
            if (!b) {
                log.error("新增城市失败");
                throw new BizException("新增城市失败");
            }
        }
        if (!simpleCitieUpdates.isEmpty()) {
            boolean b = simpleCityService.updateBatchById(simpleCitieUpdates);
            if (!b) {
                log.error("更新城市失败");
                throw new BizException("更新城市失败");
            }
        }
    }

    @Override
    public Object getWeather(HttpServletRequest request, Integer cityId) {
        if (cityId != null) {
            SimpleCity one = simpleCityService.getOne(new LambdaQueryWrapper<SimpleCity>().eq(SimpleCity::getSimpleCityId, cityId));
            if (one == null) {
                throw new BizException("1002", "不支持该城市");
            }
        }
        String ipRegion = IpUtil.getIPRegion(request);
        if (ipRegion == null) {
            throw new BizException("获取IP失败,请联系管理员");
        }
        String[] split = ipRegion.split("\\|");
        if (split.length < 4) {
            throw new BizException("1003", "未知ip归属地");
        }
        String province = split[2];
        String city = split[3];
        SimpleCity simpleCity = simpleCityMapper.getIpOne(province, city);
        if (simpleCity == null) {
            throw new BizException("1002", "不支持该ip归属城市");
        }
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("key", simpleWeatherPrefix.getApikey());
        mapParam.put("city", String.valueOf(simpleCity.getSimpleCityId()));
        Map<String, Object> mapObject = HttpUtil.getMapObject(simpleWeatherPrefix.getQuery(), mapParam, null);
        return mapObject.get("result");
    }
}
