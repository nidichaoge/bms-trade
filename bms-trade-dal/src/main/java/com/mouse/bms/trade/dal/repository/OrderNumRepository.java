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
 * @fileName : RedisRepository
 * @date : 2019/3/22 17:21
 * @description :
 */
@Repository
public class OrderNumRepository {

    private static final String PREFIX = "bms_trade:orderNum:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(Long businessId,Integer num, LocalDate localDate){
        String key = PREFIX+businessId+localDate;
        System.out.println(key);
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(s)){
            stringRedisTemplate.opsForValue().set(key, num.toString());
        }else {
            int nums = Integer.valueOf(s) + num;
            stringRedisTemplate.opsForValue().set(key, Integer.toString(nums));
        }
    }

    public Long getKey(Long businessId,LocalDate localDate){
        String key = PREFIX+businessId+localDate;
        return Long.valueOf(stringRedisTemplate.opsForValue().get(key));
    }

}
