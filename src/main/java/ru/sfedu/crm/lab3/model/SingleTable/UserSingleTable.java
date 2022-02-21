package ru.sfedu.crm.lab3.model.SingleTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class User
 */

@Entity(name = "UserSingleTable")
@Table(schema = "lab3_st", name = "User_Single_Table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("UserSingleTable")
public class UserSingleTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long birthDate;
    private String phoneNumber;

    public UserSingleTable() {}

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = System.currentTimeMillis();
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

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
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
        UserSingleTable userSingleTable = (UserSingleTable) o;
        return id == userSingleTable.id && birthDate == userSingleTable.birthDate && Objects.equals(name, userSingleTable.name) && Objects.equals(phoneNumber, userSingleTable.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

