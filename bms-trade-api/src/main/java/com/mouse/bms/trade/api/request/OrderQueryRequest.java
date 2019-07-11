package com.mouse.bms.trade.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderQueryRequest
 * @date : 2019/3/3 14:07
 * @description :
 */
@Data
@Accessors(chain = true)
public class OrderQueryRequest implements Serializable {

    private static final long serialVersionUID = 1583222079459677117L;

    private Long businessId;

    private String order;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

}
