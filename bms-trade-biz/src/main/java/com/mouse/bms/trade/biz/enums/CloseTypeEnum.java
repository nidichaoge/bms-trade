package com.mouse.bms.trade.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CloseTypeEnum
 * @date : 2019/3/26 14:36
 * @description :
 */
@Getter
public enum CloseTypeEnum {

    NO_CLOSE(0,"未关闭"),
    TIME_OUT(1,"超时关闭");

    private final int value;
    private final String description;

    CloseTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;

    }

    public static CloseTypeEnum sourceOf(int value){
        for (CloseTypeEnum item : CloseTypeEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
