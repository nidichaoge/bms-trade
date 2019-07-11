package com.mouse.bms.trade.biz.service;

import com.mouse.bms.trade.api.service.TradeStatisticsService;
import com.mouse.bms.trade.common.enums.CommonResultEnum;
import com.mouse.bms.trade.common.response.PlainResult;
import com.mouse.bms.trade.common.util.PlainResults;
import com.mouse.bms.trade.dal.repository.OrderNumRepository;
import com.mouse.bms.trade.dal.repository.SaleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TradeStatisticsServiceImpl
 * @date : 2019/3/22 15:17
 * @description :
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = TradeStatisticsService.class)
public class TradeStatisticsServiceImpl implements TradeStatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeStatisticsServiceImpl.class);

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private OrderNumRepository orderNumRepository;
    @Resource
    private SaleRepository saleRepository;

    @Override
    public PlainResult<Long> countTodayOrderNum(Long businessId) {
        try {
            Long count = orderNumRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countTodayOrderNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countTodayOrderNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countWeekOrderNum(Long businessId) {
        try {
            Long count = orderNumRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countWeekOrderNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countWeekOrderNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countTodaySaleNum(Long businessId) {
        try {
            Long count = saleRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countTodaySaleNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countTodaySaleNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countWeekSaleNum(Long businessId) {
        try {
            Long count = saleRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countWeekSaleNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countWeekSaleNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countPendingOrderNum(Long businessId) {
        try {
            Long count = saleRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countPendingOrderNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countPendingOrderNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countCornerOrderNum(Long businessId) {
        try {
            Long count = saleRepository.getKey(businessId, LocalDate.now());
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | countCornerOrderNum, param is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | countCornerOrderNum, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

}
