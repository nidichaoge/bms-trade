package com.mouse.bms.trade.dal.repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : SaleRepository
 * @date : 2019/3/26 18:15
 * @description :
 */
@Repository
public class SaleRepository {

    private static final String PREFIX = "bms_trade:sale:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(Long businessId,Long num, LocalDate localDate){
        String key = PREFIX+businessId+localDate;
        System.out.println(key);
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(s)){
            stringRedisTemplate.opsForValue().set(key, num.toString());
        }else {
            long nums = Long.valueOf(s) + num;
            stringRedisTemplate.opsForValue().set(key, Long.toString(nums));
        }
    }

    public Long getKey(Long businessId,LocalDate localDate){
        String key = PREFIX+businessId+localDate;
        return Long.valueOf(stringRedisTemplate.opsForValue().get(key));
    }

}
