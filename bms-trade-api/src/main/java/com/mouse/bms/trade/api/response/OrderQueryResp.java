package com.mouse.bms.trade.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderQueryResp
 * @date : 2019/3/3 14:08
 * @description :
 */
@Data
@Accessors(chain = true)
public class OrderQueryResp implements Serializable {

    private static final long serialVersionUID = -3931987036735137516L;

    private Long id;

    private String orderNo;

    private String orderStatus;

    private String payType;

    private String payWayType;

    private LocalDateTime createdAt;

    private String remark;

}
