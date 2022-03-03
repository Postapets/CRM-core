package ru.sfedu.crm.lab4.model.set;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "UserSet")
@Table(schema="lab4_set", name = "User_Set")
public class UserSet  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema="lab4_set")
    private Set<Long> OrdersId =  new HashSet<>();
    private String phoneNumber;

    public UserSet() {
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

    public Set<Long> getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(Set<Long> ordersId) {
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
        UserSet userSet = (UserSet) o;
        return id == userSet.id && Objects.equals(name, userSet.name) && Objects.equals(OrdersId, userSet.OrdersId) && Objects.equals(phoneNumber, userSet.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, OrdersId, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", OrdersId=" + OrdersId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
