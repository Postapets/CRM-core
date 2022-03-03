package ru.sfedu.crm.lab4.model.collection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "UserCollection")
@Table(schema="lab4_collection", name = "User_Collection")
public class UserCollection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema="lab4_collection")
    private Set<Order> order = new HashSet<>();
    private String phoneNumber;

    public UserCollection() {
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

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
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
        UserCollection that = (UserCollection) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(order, that.order) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
