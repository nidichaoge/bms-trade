package com.mouse.bms.trade.dal.dataobject;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PriceDO
 * @date : 2019/3/22 10:47
 * @description :
 */
@Data
@Accessors(chain = true)
public class PriceDO {

    private Long originPrice;

    private Long currentPrice;

    private Long postage;

    private Long totalPrice;

    private Long promotionAmount;

}
