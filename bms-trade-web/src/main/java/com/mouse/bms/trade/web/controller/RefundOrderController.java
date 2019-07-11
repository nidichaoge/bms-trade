package com.mouse.bms.trade.web.controller;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.trade.api.request.RefundOrderQueryRequest;
import com.mouse.bms.trade.api.response.RefundOrderDetailResp;
import com.mouse.bms.trade.api.response.RefundOrderQueryResp;
import com.mouse.bms.trade.api.service.RefundOrderService;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundOrderController
 * @date : 2019/3/22 12:20
 * @description :
 */
@RestController
@RequestMapping("/refund")
public class RefundOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundOrderController.class);

    @Resource
    private RefundOrderService refundOrderService;

    @ApiOperation(value = "逆向订单列表")
    @GetMapping("/list")
    public ListResult<RefundOrderQueryResp> listRefundOrder(@RequestParam String query) {
        RefundOrderQueryRequest refundOrderQueryRequest = JSON.parseObject(query, RefundOrderQueryRequest.class);
        LOGGER.info("RefundOrderController | listRefundOrder, refundOrderQueryRequest:{}.", refundOrderQueryRequest);
        return refundOrderService.listRefundOrder(refundOrderQueryRequest);
    }

    @ApiOperation(value = "确认退货")
    @PutMapping("/plus")
    public PlainResult<Boolean> plusRefundOrderStatus(@RequestParam Long businessId, @RequestParam String orderNo) {
        LOGGER.info("RefundOrderController | plusRefundOrderStatus, businessId:{}, orderNo:{}.", businessId, orderNo);
        return refundOrderService.plusRefundOrderStatus(businessId, orderNo);
    }

    @ApiOperation(value = "逆向订单详情")
    @GetMapping("/get/detail")
    public PlainResult<RefundOrderDetailResp> searchRefundOrderDetail(@RequestParam Long businessId,
                                                                      @RequestParam String orderNo) {
        LOGGER.info("RefundOrderController | searchRefundOrderDetail, businessId:{}, orderNo:{}.", businessId, orderNo);
        return refundOrderService.searchRefundOrderDetail(businessId, orderNo);
    }

}
