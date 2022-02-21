package ru.sfedu.crm.lab3.model.TablePerClass;

import ru.sfedu.crm.enums.ClientStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class Client
 */

@Entity(name = "ClientTablePerClass")
@Table(schema = "lab3_tpc",name = "Client_Table_Per_Class")
public class ClientTablePerClass extends UserTablePerClass {
    private long inn;
    private ClientStatus status;

    public ClientTablePerClass() {}

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public void setDefaultStatus() {
        this.status = ClientStatus.NEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientTablePerClass clientTablePerClass = (ClientTablePerClass) o;
        return inn == clientTablePerClass.inn && status == clientTablePerClass.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inn, status);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", birthDate=" + super.getBirthDate() +
                ", phoneNumber='" + super.getPhoneNumber() + '\'' +
                "inn=" + inn +
                ", status=" + status +
                '}';
    }
}
