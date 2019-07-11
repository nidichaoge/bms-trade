package com.mouse.bms.trade.dal.dataobject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundOrderDO
 * @date : 2019/3/26 14:29
 * @description :
 */
@Data
@Accessors(chain = true)
@Document(collection = "bms_refund_order")
public class RefundOrderDO {

    @Id
    private Long id;

    @Indexed(unique = true)
    private String orderNo;

    private Integer orderType;

    private Integer orderStatus;

    private Integer payType;

    private Integer payWayType;

    private Integer activityType;

    private Integer closeType;

    private PriceDO priceDO;

    private BuyerDO buyerDO;

    private GoodsDO goodsDO;

    private LocalDateTime createdTime;

    private LocalDateTime closedTime;

    private LocalDateTime payTime;

    private LocalDateTime shipTime;

    private LocalDateTime receiveTime;

    private String remark;

}
