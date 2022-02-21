package ru.sfedu.crm.lab3.model.JoinedTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class User
 */
@Entity(name = "UserJoinedTable")
@Table(schema="lab3_jt", name = "User_Joined_Table")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserJoinedTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long birthDate;
    private String phoneNumber;

    public UserJoinedTable() {}

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
        UserJoinedTable userJoinedTable = (UserJoinedTable) o;
        return id == userJoinedTable.id && birthDate == userJoinedTable.birthDate && Objects.equals(name, userJoinedTable.name) && Objects.equals(phoneNumber, userJoinedTable.phoneNumber);
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

