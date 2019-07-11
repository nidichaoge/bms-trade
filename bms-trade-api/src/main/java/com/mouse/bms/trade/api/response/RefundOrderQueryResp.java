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
 * @fileName : RefundOrderQueryResp
 * @date : 2019/3/22 14:57
 * @description :
 */
@Data
@Accessors(chain = true)
public class RefundOrderQueryResp implements Serializable {

    private static final long serialVersionUID = 3601107233741773759L;

    private Long id;

    private String orderNo;

    private String remark;

    private LocalDateTime closeTime;

}
