package com.mouse.bms.trade.dal.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BuyerDO
 * @date : 2019/3/22 10:48
 * @description :
 */
@Data
@Accessors(chain = true)
public class BuyerDO {

    private Long buyerId;

    private String telephone;

}
