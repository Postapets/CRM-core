package ru.sfedu.crm.lab4.model.collection;

import ru.sfedu.crm.enums.OrderType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Order {

    @Column(nullable = false)
    private OrderType orderType;
    @Column(nullable = false)
    private Long id;

    public Order() {
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderType == order.orderType && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderType, id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderType=" + orderType +
                ", id=" + id +
                '}';
    }
}
