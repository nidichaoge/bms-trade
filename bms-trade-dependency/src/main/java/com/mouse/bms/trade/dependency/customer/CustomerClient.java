package com.mouse.bms.trade.dependency.customer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.api.service.CustomerService;
import com.mouse.bms.customer.common.response.PlainResult;
import com.mouse.bms.trade.dependency.entity.CustomerInfoEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerClient
 * @date : 2019/3/29 11:55
 * @description :
 */
@Component
public class CustomerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerClient.class);

//    @Resource
    @Reference(url = "dubbo://127.0.0.1:20880")
    private CustomerService customerService;

    public CustomerInfoEntity searchCustomer(Long businessId, String telephone) {
        try {
            PlainResult<CustomerQueryResp> result = customerService.searchCustomer(businessId, telephone);
            if (result.isSuccess() && !Objects.isNull(result.getData())) {
                CustomerQueryResp data = result.getData();
                return buildCustomerInfoEntity(data);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("CustomerClient | searchCustomer, exception, msg:{}.", e.getMessage());
            return null;
        }
    }

    private CustomerInfoEntity buildCustomerInfoEntity(CustomerQueryResp customerQueryResp) {
        return new CustomerInfoEntity()
            .setCustomerId(customerQueryResp.getCustomerId())
            .setMobile(customerQueryResp.getMobile())
            .setNickName(customerQueryResp.getNickName());
    }

}
