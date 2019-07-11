package com.mouse.bms.trade.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderCreateRequest
 * @date : 2019/3/5 20:50
 * @description :
 */
@Data
@Accessors(chain = true)
public class OrderCreateRequest implements Serializable {

    private static final long serialVersionUID = 3696095618656119735L;

    private Long id;


}
