package com.mouse.bms.trade.dependency.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerInfoEntity
 * @date : 2019/3/29 12:42
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerInfoEntity {

    private Long customerId;

    private String nickName;

    private String mobile;

}
