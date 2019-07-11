package com.mouse.bms.trade.biz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.mongodb.client.result.UpdateResult;
import com.mouse.bms.trade.api.request.OrderQueryRequest;
import com.mouse.bms.trade.api.response.OrderDetailResp;
import com.mouse.bms.trade.api.response.OrderQueryResp;
import com.mouse.bms.trade.api.service.OrderService;
import com.mouse.bms.trade.biz.enums.OrderStatusEnum;
import com.mouse.bms.trade.biz.enums.PayTypeEnum;
import com.mouse.bms.trade.biz.enums.PayWayEnum;
import com.mouse.bms.trade.common.enums.CommonResultEnum;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;
import com.mouse.bms.trade.common.util.ListResults;
import com.mouse.bms.trade.common.util.PlainResults;
import com.mouse.bms.trade.dal.dataobject.OrderDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : OrderServiceImpl
 * @date : 2019/3/22 14:46
 * @description :
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public ListResult<OrderQueryResp> listOrder(OrderQueryRequest orderQueryRequest) {
        try {
            Long businessId = orderQueryRequest.getBusinessId();
            String order = orderQueryRequest.getOrder();
            String orderBy = orderQueryRequest.getOrderBy();
            Integer page = orderQueryRequest.getPage();
            Integer pageSize = orderQueryRequest.getPageSize();
            Criteria criteria = Criteria.where("businessId").is(businessId);
            Sort sort;
            if (StringUtils.isEmpty(order) || "desc".equals(order)) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }

            Query query = new Query(criteria)
                .with(sort).skip(page * pageSize).limit(pageSize);
            List<OrderDO> orderDOS = mongoTemplate.find(query, OrderDO.class);
            System.err.println(orderDOS);
            if (CollectionUtils.isEmpty(orderDOS)) {
                return ListResults.success(Lists.newArrayList());
            }
            return ListResults.success(
                orderDOS.stream().filter(Objects::nonNull).map(this::buildOrderQueryResp).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("OrderService | listOrder, param is illegal, orderQueryRequest:{}.", orderQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | listOrder, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> plusOrderStatus(Long businessId, String orderNo) {
        try {
            UpdateResult
                updateResult =
                mongoTemplate
                    .updateFirst(new Query(Criteria.where("orderNo").is(orderNo)), new Update().set("orderStatus", 1),
                                 OrderDO.class);
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | plusOrderStatus, param is illegal, businessId:{}, orderDO:{}.", businessId,
                      orderNo);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | plusOrderStatus, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> plusOrderStatusBatch(Long businessId, List<String> orderNos) {
        try {
            UpdateResult
                updateResult =
                mongoTemplate
                    .updateMulti(new Query(Criteria.where("orderNo").in(orderNos)), new Update().set("orderStatus", 1),
                                 OrderDO.class);
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | plusOrderStatusBatch, param is illegal, businessId:{}, orderDO:{}.", businessId,
                      orderNos);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | plusOrderStatusBatch, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<OrderDetailResp> searchOrderDetail(Long businessId, String orderNo) {
        try {
            Query query = new Query(Criteria.where("orderNo").is(orderNo));
            OrderDO orderDO = mongoTemplate.findOne(query, OrderDO.class);
            if (!Objects.isNull(orderDO)) {
                return PlainResults.success(buildOrderDetailResp(orderDO));
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("OrderService | createOrder, param is illegal, businessId:{}, orderDO:{}.", businessId, orderNo);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | createOrder, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> createOrder(OrderDO orderDO) {
        try {
            OrderDO insert = mongoTemplate.insert(orderDO);
            if (!Objects.isNull(insert)) {
                return PlainResults.success(insert.getId());
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("OrderService | createOrder, param is illegal, orderDO:{}.", orderDO);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("OrderService | createOrder, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private OrderQueryResp buildOrderQueryResp(OrderDO orderDO) {
        return new OrderQueryResp()
            .setId(orderDO.getId())
            .setOrderNo(orderDO.getOrderNo())
            .setCreatedAt(orderDO.getCreatedTime())
            .setOrderStatus(OrderStatusEnum.sourceOf(orderDO.getOrderStatus()).getDescription())
            .setPayType(PayTypeEnum.sourceOf(orderDO.getPayType()).getDescription())
            .setPayWayType(PayWayEnum.sourceOf(orderDO.getPayWayType()).getDescription())
            .setRemark(orderDO.getRemark());
    }

    private OrderDetailResp buildOrderDetailResp(OrderDO orderDO) {
        OrderDetailResp orderDetailResp = new OrderDetailResp();
        BeanUtils.copyProperties(orderDO, orderDetailResp);
        return orderDetailResp;
    }

}
