package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderStatusEnum
 * @date : 2019/3/26 14:35
 * @description :
 */
@Getter
public enum  OrderStatusEnum {

    UNPAID(0,"待支付"),
    DELIVERY(1,"待发货"),
    RECEIVE(2,"待收货");

    private final int value;
    private final String description;

    OrderStatusEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static OrderStatusEnum sourceOf(int value){
        for (OrderStatusEnum item : OrderStatusEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
