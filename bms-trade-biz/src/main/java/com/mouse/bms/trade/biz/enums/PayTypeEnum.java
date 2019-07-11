package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PayTypeEnum
 * @date : 2019/3/26 14:34
 * @description :
 */
@Getter
public enum  PayTypeEnum {


    NO_PAY(0, "未支付"),
    ALREADY_PAY(1,"已支付");
    private final int value;
    private final String description;

    PayTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static PayTypeEnum sourceOf(int value){
        for (PayTypeEnum item : PayTypeEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
