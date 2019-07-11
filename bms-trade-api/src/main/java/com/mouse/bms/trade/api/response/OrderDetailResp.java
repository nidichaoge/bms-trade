package com.mouse.bms.trade.api.response;

import com.mouse.bms.trade.dal.dataobject.BuyerDO;
import com.mouse.bms.trade.dal.dataobject.GoodsDO;
import com.mouse.bms.trade.dal.dataobject.PriceDO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderDetailResp
 * @date : 2019/3/22 14:54
 * @description :
 */
@Data
@Accessors(chain = true)
public class OrderDetailResp implements Serializable {

    private static final long serialVersionUID = -2351843532337017448L;

    private Long id;

    private String orderNo;

    private Long businessId;

    private Integer orderType;

    private Integer orderStatus;

    private Integer payType;

    private Integer payWayType;

    private Integer activityType;

    private Integer closeType;

    private PriceDO priceDO;

    private BuyerDO buyerDO;

    private GoodsDO goodsDO;

    private Date createdTime;

    private Date closedTime;

    private Date payTime;

    private Date shipTime;

    private Date receiveTime;

    private String remark;

}
