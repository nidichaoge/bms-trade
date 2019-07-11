package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PayWayEnum
 * @date : 2019/3/26 16:59
 * @description :
 */
@Getter
public enum PayWayEnum {

    ALI_PAY(0, "支付宝支付"),
    WECHAT_PAY(1,"微信支付"),
    CASH_PAY(2,"现金支付");

    private final int value;
    private final String description;

    PayWayEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static PayWayEnum sourceOf(int value){
        for (PayWayEnum item : PayWayEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
