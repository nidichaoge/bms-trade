package com.mouse.bms.trade.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundOrderQueryRequest
 * @date : 2019/3/22 14:57
 * @description :
 */
@Data
@Accessors(chain = true)
public class RefundOrderQueryRequest implements Serializable {

    private static final long serialVersionUID = -6406265843768906367L;

    private Long businessId;

    private String order;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

}
