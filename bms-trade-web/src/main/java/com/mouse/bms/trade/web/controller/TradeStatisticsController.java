package com.mouse.bms.trade.web.controller;

import com.mouse.bms.trade.api.service.TradeStatisticsService;
import com.mouse.bms.trade.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TradeStatisticsController
 * @date : 2019/3/22 11:43
 * @description :
 */
@RestController
@RequestMapping("/trade")
public class TradeStatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeStatisticsController.class);

    @Resource
    private TradeStatisticsService tradeStatisticsService;

    @ApiOperation(value = "今日订单数")
    @GetMapping("/today/order")
    public PlainResult<Long> countTodayOrderNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countTodayOrderNum, businessId:{}.", businessId);
        return tradeStatisticsService.countTodayOrderNum(businessId);
    }

    @ApiOperation(value = "近七天订单数")
    @GetMapping("/week/order")
    public PlainResult<Long> countWeekOrderNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countWeekOrderNum, businessId:{}.", businessId);
        return tradeStatisticsService.countWeekOrderNum(businessId);
    }

    @ApiOperation(value = "今日销售额")
    @GetMapping("/today/sale")
    public PlainResult<Long> countTodaySaleNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countTodaySaleNum, businessId:{}.", businessId);
        return tradeStatisticsService.countTodaySaleNum(businessId);
    }

    @ApiOperation(value = "近七天销售额")
    @GetMapping("/week/sale")
    public PlainResult<Long> countWeekSaleNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countWeekSaleNum, businessId:{}.", businessId);
        return tradeStatisticsService.countWeekSaleNum(businessId);
    }

    @ApiOperation(value = "待处理订单数")
    @GetMapping("/pending/order")
    public PlainResult<Long> countPendingOrderNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countPendingOrderNum, businessId:{}.", businessId);
        return tradeStatisticsService.countPendingOrderNum(businessId);
    }

    @ApiOperation(value = "待发货订单数")
    @GetMapping("/corner/order")
    public PlainResult<Long> countCornerOrderNum(@RequestParam Long businessId) {
        LOGGER.info("TradeStatisticsController | countCornerOrderNum, businessId:{}.", businessId);
        return tradeStatisticsService.countCornerOrderNum(businessId);
    }

}
