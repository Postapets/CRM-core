package ru.sfedu.crm.lab3.model.TablePerClass;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class User
 */
@Entity(name = "UserTablePerClass")
@Table(schema="lab3_tpc", name = "User_Table_Per_Class")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserTablePerClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long birthDate;
    private String phoneNumber;

    public UserTablePerClass() {}

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
        UserTablePerClass userTablePerClass = (UserTablePerClass) o;
        return id == userTablePerClass.id && birthDate == userTablePerClass.birthDate && Objects.equals(name, userTablePerClass.name) && Objects.equals(phoneNumber, userTablePerClass.phoneNumber);
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

