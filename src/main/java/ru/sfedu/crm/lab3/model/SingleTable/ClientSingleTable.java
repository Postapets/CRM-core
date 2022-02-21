package ru.sfedu.crm.lab3.model.SingleTable;

import ru.sfedu.crm.enums.ClientStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class Client
 */
@Entity(name = "ClientSingleTable")
@Table(schema = "lab3_st")
@DiscriminatorValue("ClientSingleTable")
public class ClientSingleTable extends UserSingleTable {
    private long inn;
    private ClientStatus status;

    public ClientSingleTable() {
    }

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
        ClientSingleTable clientSingleTable = (ClientSingleTable) o;
        return inn == clientSingleTable.inn && status == clientSingleTable.status;
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
