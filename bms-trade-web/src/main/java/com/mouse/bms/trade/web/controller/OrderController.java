package com.mouse.bms.trade.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.api.service.CustomerService;
import com.mouse.bms.trade.api.request.OrderQueryRequest;
import com.mouse.bms.trade.api.response.OrderDetailResp;
import com.mouse.bms.trade.api.response.OrderQueryResp;
import com.mouse.bms.trade.api.service.OrderService;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;
import com.mouse.bms.trade.common.util.PlainResults;
import com.mouse.bms.trade.dal.dataobject.OrderDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @fileName : OrderController
 * @date : 2019/3/22 11:39
 * @description :
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private CustomerService customerService;

    @GetMapping("/hello")
    public PlainResult<Boolean> hello(@RequestParam Long businessId, @RequestParam String mobile) {
        com.mouse.bms.customer.common.response.PlainResult<CustomerQueryResp>
            result =
            customerService.searchCustomer(businessId, mobile);
        LOGGER.info("{}",result);
        return PlainResults.success(Boolean.TRUE);
    }

    @ApiOperation(value = "订单列表")
    @GetMapping("/list")
    public ListResult<OrderQueryResp> listOrder(@RequestParam String query) {
        OrderQueryRequest orderQueryRequest = JSON.parseObject(query, OrderQueryRequest.class);
        LOGGER.info("OrderController | listOrder, orderQueryRequest:{}.", orderQueryRequest);
        return orderService.listOrder(orderQueryRequest);
    }

    @ApiOperation(value = "订单发货")
    @PutMapping("/plus")
    public PlainResult<Boolean> plusOrderStatus(@RequestParam Long businessId, @RequestParam String orderNo) {
        LOGGER.info("OrderController | plusOrderStatus, businessId:{}, orderNo:{}.", businessId, orderNo);
        return orderService.plusOrderStatus(businessId, orderNo);
    }

    @ApiOperation(value = "订单批量发货")
    @PutMapping("/plusbatch")
    public PlainResult<Boolean> plusOrderStatusBatch(@RequestParam Long businessId,
                                                     @RequestParam List<String> orderNos) {
        LOGGER.info("OrderController | plusOrderStatusBatch, businessId:{}, orderNos:{}.", businessId, orderNos);
        return orderService.plusOrderStatusBatch(businessId, orderNos);
    }

    @ApiOperation(value = "订单详情")
    @GetMapping("/get/detail")
    public PlainResult<OrderDetailResp> searchOrderDetail(@RequestParam Long businessId, @RequestParam String orderNo) {
        LOGGER.info("OrderController | searchOrderDetail, businessId:{}, orderNo:{}.", businessId, orderNo);
        return orderService.searchOrderDetail(businessId, orderNo);
    }

    @ApiOperation(value = "create order")
    @PostMapping("/create")
    public PlainResult<Long> createOrder(@RequestBody OrderDO orderDO) {
        LOGGER.info("OrderController | listOrder, orderDO:{}.", orderDO);
        return orderService.createOrder(orderDO);
    }

}
