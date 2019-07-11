package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderTypeEnum
 * @date : 2019/3/26 14:33
 * @description :
 */
@Getter
public enum OrderTypeEnum {

    NORMAL(0, "普通订单");

    private final int value;
    private final String description;

    OrderTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static OrderTypeEnum sourceOf(int value){
        for (OrderTypeEnum item : OrderTypeEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
