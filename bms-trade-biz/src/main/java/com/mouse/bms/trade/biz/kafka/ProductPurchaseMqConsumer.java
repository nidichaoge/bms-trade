package com.mouse.bms.trade.biz.kafka;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.product.api.dto.ProductPurchaseDTO;
import com.mouse.bms.trade.dal.dataobject.BuyerDO;
import com.mouse.bms.trade.dal.dataobject.GoodsDO;
import com.mouse.bms.trade.dal.dataobject.OrderDO;
import com.mouse.bms.trade.dal.dataobject.PriceDO;
import com.mouse.bms.trade.dependency.customer.CustomerClient;
import com.mouse.bms.trade.dependency.entity.CustomerInfoEntity;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductPurchaseMqConsumer
 * @date : 2019/3/29 11:22
 * @description :
 */
@Component
public class ProductPurchaseMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPurchaseMqConsumer.class);

    @Resource
    private CustomerClient customerClient;
    @Resource
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = {TopicCommon.PRODUCT_PURCHASE_TOPIC})
    public void receive(ConsumerRecord record) {
        try {
            ProductPurchaseDTO productPurchaseDTO =
                JSON.parseObject(record.value().toString(), ProductPurchaseDTO.class);
            Long businessId = productPurchaseDTO.getBusinessId();
            Long productId = productPurchaseDTO.getProductId();
            String telephone = productPurchaseDTO.getTelephone();
            Long postage = productPurchaseDTO.getPostage();
            Integer number = productPurchaseDTO.getNumber();
            Long realPay = productPurchaseDTO.getRealPay();
            CustomerInfoEntity customer = customerClient.searchCustomer(businessId, telephone);
            OrderDO orderDO = new OrderDO();
            orderDO.setBusinessId(businessId)
                .setOrderNo(OrderNoGenerateFactory.generateOrderNo(businessId, customer.getCustomerId(), productId))
                .setActivityType(0)
                .setBuyerDO(new BuyerDO().setBuyerId(customer.getCustomerId()).setTelephone(telephone))
                .setPriceDO(new PriceDO().setPostage(postage).setTotalPrice(realPay))
                .setGoodsDO(new GoodsDO().setGoodsId(productId))
                .setCreatedTime(LocalDateTime.now())
//                .setReceiveTime()
//                .setClosedTime()
                .setCloseType(0)
//                .setShipTime()
//                .setPayTime()
                .setPayType(1)
                .setPayWayType(0)
                .setRemark(productPurchaseDTO.getRemark())
//                .setReceiveTime()
                .setOrderStatus(1)
                .setOrderType(0);
            mongoTemplate.insert(orderDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
