package ru.sfedu.crm.lab3.model.JoinedTable;

import ru.sfedu.crm.enums.ClientStatus;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Client
 */
@Entity(name = "ClientJoinedTable")
@Table(schema = "lab3_jt", name ="Client_Joined_Table" )
@PrimaryKeyJoinColumn(name = "user_id")
public class ClientJoinedTable extends UserJoinedTable {
    private long inn;
    private ClientStatus status;

    public ClientJoinedTable() {}

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
        ClientJoinedTable client = (ClientJoinedTable) o;
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
