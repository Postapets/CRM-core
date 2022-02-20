package ru.sfedu.crm.lab3.model.JoinedTable;

import ru.sfedu.crm.enums.ClientStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class Client
 */
@Entity(name = "JT_Client")
@Table(schema = "lab3", name ="JT_Client" )
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User implements Serializable {
    private long inn;
    private ClientStatus status;

    public Client() {}

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
        Client client = (Client) o;
        return inn == client.inn && status == client.status;
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
