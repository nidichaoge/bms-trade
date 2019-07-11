package com.mouse.bms.trade.biz.kafka;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderNoGenerateFactory
 * @date : 2019/3/29 16:12
 * @description :
 */
public class OrderNoGenerateFactory {

    private static final String DATE_PATTERN =  "yyyyMMddHHmmss";

    public static String generateOrderNo(Long businessId, Long buyerId, Long goodsId){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String format = dateTimeFormatter.format(localDateTime);
        int a = businessId.hashCode()+buyerId.hashCode()+goodsId.hashCode();
        int b =a &7;
        return "E"+format+b;
    }

}