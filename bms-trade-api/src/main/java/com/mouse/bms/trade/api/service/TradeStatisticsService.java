package com.mouse.bms.trade.api.service;

import com.mouse.bms.trade.common.response.PlainResult;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TradeStatisticsService
 * @date : 2019/3/22 14:47
 * @description :
 */
public interface TradeStatisticsService {

    /**
     * 今日订单数
     * @param businessId
     * @return
     */
    PlainResult<Long> countTodayOrderNum(Long businessId);

    /**
     * 近七天订单数
     * @param businessId
     * @return
     */
    PlainResult<Long> countWeekOrderNum(Long businessId);

    /**
     * 今日销售额
     * @param businessId
     * @return
     */
    PlainResult<Long> countTodaySaleNum(Long businessId);

    /**
     * 近七天销售额
     * @param businessId
     * @return
     */
    PlainResult<Long> countWeekSaleNum(Long businessId);

    /**
     * 待处理订单数
     * @param businessId
     * @return
     */
    PlainResult<Long> countPendingOrderNum(Long businessId);

    /**
     * 待发货订单数
     * @param businessId
     * @return
     */
    PlainResult<Long> countCornerOrderNum(Long businessId);

}
