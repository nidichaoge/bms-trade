package com.mouse.bms.trade.api.service;

import com.mouse.bms.trade.api.request.RefundOrderQueryRequest;
import com.mouse.bms.trade.api.response.RefundOrderDetailResp;
import com.mouse.bms.trade.api.response.RefundOrderQueryResp;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundOrderService
 * @date : 2019/3/22 14:47
 * @description :
 */
public interface RefundOrderService {

    /**
     * 逆向订单列表
     * @param refundOrderQueryRequest
     * @return
     */
    ListResult<RefundOrderQueryResp> listRefundOrder(RefundOrderQueryRequest refundOrderQueryRequest);

    /**
     * 确认退货
     * @param businessId
     * @param refundOrderNo
     * @return
     */
    PlainResult<Boolean> plusRefundOrderStatus(Long businessId, String refundOrderNo);

    /**
     * 逆向订单详情
     * @param businessId
     * @param refundOrderNo
     * @return
     */
    PlainResult<RefundOrderDetailResp> searchRefundOrderDetail(Long businessId, String refundOrderNo);

}
