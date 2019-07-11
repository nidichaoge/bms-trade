package com.mouse.bms.trade.biz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.mongodb.client.result.UpdateResult;
import com.mouse.bms.trade.api.request.RefundOrderQueryRequest;
import com.mouse.bms.trade.api.response.RefundOrderDetailResp;
import com.mouse.bms.trade.api.response.RefundOrderQueryResp;
import com.mouse.bms.trade.api.service.RefundOrderService;
import com.mouse.bms.trade.common.enums.CommonResultEnum;
import com.mouse.bms.trade.common.response.ListResult;
import com.mouse.bms.trade.common.response.PlainResult;
import com.mouse.bms.trade.common.util.ListResults;
import com.mouse.bms.trade.common.util.PlainResults;
import com.mouse.bms.trade.dal.dataobject.OrderDO;
import com.mouse.bms.trade.dal.dataobject.RefundOrderDO;

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
 * @fileName : RefundOrderServiceImpl
 * @date : 2019/3/22 15:17
 * @description :
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = RefundOrderService.class)
public class RefundOrderServiceImpl implements RefundOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundOrderServiceImpl.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public ListResult<RefundOrderQueryResp> listRefundOrder(RefundOrderQueryRequest refundOrderQueryRequest) {
        try {
            Long businessId = refundOrderQueryRequest.getBusinessId();
            String order = refundOrderQueryRequest.getOrder();
            String orderBy = refundOrderQueryRequest.getOrderBy();
            Integer page = refundOrderQueryRequest.getPage();
            Integer pageSize = refundOrderQueryRequest.getPageSize();
            Criteria criteria = Criteria.where("businessId").is(businessId);
            Sort sort;
            if (StringUtils.isEmpty(order) || "desc".equals(order)) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }

            Query query = new Query(criteria)
                .with(sort).skip(page * pageSize).limit(pageSize);
            List<RefundOrderDO> refundOrderDOS = mongoTemplate.find(query, RefundOrderDO.class);
            System.err.println(refundOrderDOS);
            if (CollectionUtils.isEmpty(refundOrderDOS)) {
                return ListResults.success(Lists.newArrayList());
            }
            return ListResults.success(
                refundOrderDOS.stream().filter(Objects::nonNull).map(this::buildRefundOrderQueryResp)
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("RefundOrderService | listRefundOrder, param is illegal, refundOrderQueryRequest:{}.",
                      refundOrderQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("RefundOrderService | listRefundOrder, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> plusRefundOrderStatus(Long businessId, String refundOrderNo) {
        try {
            UpdateResult
                updateResult =
                mongoTemplate
                    .updateFirst(new Query(Criteria.where("orderNo").is(refundOrderNo)),
                                 new Update().set("orderStatus", 1),
                                 OrderDO.class);
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("RefundOrderService | plusRefundOrderStatus, param is illegal, businessId:{}, refundOrderNo:{}.",
                      businessId, refundOrderNo);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("RefundOrderService | plusRefundOrderStatus, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<RefundOrderDetailResp> searchRefundOrderDetail(Long businessId, String refundOrderNo) {
        try {
            Query query = new Query(Criteria.where("orderNo").is(refundOrderNo));
            RefundOrderDO orderDO = mongoTemplate.findOne(query, RefundOrderDO.class);
            if (!Objects.isNull(orderDO)) {
                return PlainResults.success(buildRefundOrderDetailResp(orderDO));
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("RefundOrderService | searchRefundOrderDetail, param is illegal, businessId:{},refundOrderNo:{}.",
                      businessId, refundOrderNo);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("RefundOrderService | searchRefundOrderDetail, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private RefundOrderDetailResp buildRefundOrderDetailResp(RefundOrderDO refundOrderDO) {
        RefundOrderDetailResp refundOrderDetailResp = new RefundOrderDetailResp();
        BeanUtils.copyProperties(refundOrderDO, refundOrderDetailResp);
        return refundOrderDetailResp;
    }

    private RefundOrderQueryResp buildRefundOrderQueryResp(RefundOrderDO refundOrderDO) {
        return new RefundOrderQueryResp()
            .setId(refundOrderDO.getId())
            .setOrderNo(refundOrderDO.getOrderNo())
            .setRemark(refundOrderDO.getRemark())
            .setCloseTime(refundOrderDO.getClosedTime());
    }

}
