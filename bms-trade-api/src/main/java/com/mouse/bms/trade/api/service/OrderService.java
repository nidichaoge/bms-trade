package com.mouse.bms.trade.api.service;

import com.mouse.bms.trade.api.request.OrderQueryRequest;
import com.mouse.bms.trade.api.response.OrderDetailResp;
import com.mouse.bms.trade.api.response.OrderQueryResp;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;
import com.mouse.bms.trade.dal.dataobject.OrderDO;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderManagerService
 * @date : 2019/3/3 14:06
 * @description : 订单管理服务，订单列表使用
 */
public interface OrderService {

    /**
     * 订单列表
     * @param orderQueryRequest
     * @return
     */
    ListResult<OrderQueryResp> listOrder(OrderQueryRequest orderQueryRequest);

    /**
     * 订单发货
     * @param businessId
     * @param orderNo
     * @return
     */
    PlainResult<Boolean> plusOrderStatus(Long businessId, String orderNo);

    /**
     * 批量发货
     * @param businessId
     * @param orderNo
     * @return
     */
    PlainResult<Boolean> plusOrderStatusBatch(Long businessId, List<String> orderNo);

    /**
     * 订单详情
     * @param businessId
     * @param orderNo
     * @return
     */
    PlainResult<OrderDetailResp> searchOrderDetail(Long businessId, String orderNo);

    /**
     * 创建订单
     * @param orderDO
     * @return
     */
    PlainResult<Long> createOrder(OrderDO orderDO);

}
