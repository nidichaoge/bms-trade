package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ActivityTypeEnum
 * @date : 2019/3/26 14:35
 * @description :
 */
@Getter
public enum ActivityTypeEnum {

    NONE_ACTIVITY(0,"没有活动"),
    REDUCED_ACTIVITY(1,"满减活动"),
    ONE_CENT_ACTIVITY(2,"1分钱兑换");

    private final int value;
    private final String description;

    ActivityTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static ActivityTypeEnum sourceOf(int value){
        for (ActivityTypeEnum item : ActivityTypeEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
