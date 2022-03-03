package ru.sfedu.crm.lab4.model.map;

import ru.sfedu.crm.enums.OrderType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "UserMap")
@Table(schema="lab4_map", name = "User_Map")
public class UserMap implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema="lab4_map")
    @MapKeyColumn(name="ID")
    private Map<Long, OrderType> OrdersId = new HashMap<>();
    private String phoneNumber;

    public UserMap() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, OrderType> getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(Map<Long, OrderType> ordersId) {
        OrdersId = ordersId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMap userMap = (UserMap) o;
        return id == userMap.id && Objects.equals(name, userMap.name) && Objects.equals(OrdersId, userMap.OrdersId) && Objects.equals(phoneNumber, userMap.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, OrdersId, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserMap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", OrdersId=" + OrdersId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
