package com.order

import com.order.entity.Order
import com.order.mapper.OrderMapper
import org.jeasy.random.EasyRandom
import spock.lang.Specification

class OrderMapperTest extends Specification {

    def "map order entities to order dto list"() {
        setup:
        def generator = new EasyRandom()
        def orders = generator.objects(Order.class, 2).toList()
        when:
        def orderDtos = OrderMapper.mapEntityToOrderDto(orders)
        then:
        orders.size() == orderDtos.size()
        for (int i = 0; i < orders.size(); i++) {
            assert orders[i].getCancelReason() == orderDtos[i].getCancelReason()
            assert orders[i].getCreatedTime() == orderDtos[i].getCreatedTime()
            assert orders[i].getId() == orderDtos[i].getId()
            assert orders[i].getLastModifiedTime() == orderDtos[i].getLastModifiedTime()
            assert orders[i].getStatus() == orderDtos[i].getStatus()
        }
    }
}
