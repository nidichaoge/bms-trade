package com.mouse.bms.trade.dal.repository;

import com.mouse.bms.trade.dal.dataobject.OrderDO;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : MongoRepository
 * @date : 2019/3/22 10:42
 * @description :
 */
@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderDO,Long> {


}
