package ru.sfedu.crm.lab3.model.SingleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class Worker
 */
@Entity(name = "WorkerSingleTable")
@Table(schema = "lab3_st")
@DiscriminatorValue("WorkerSingleTable")
public class WorkerSingleTable extends UserSingleTable {
    private String position;
    private long employmentDate;
    private long leaveDate;

    public WorkerSingleTable() {}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(long employmentDate) {
        this.employmentDate = employmentDate;
    }

    public long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(long leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WorkerSingleTable workerSingleTable = (WorkerSingleTable) o;
        return employmentDate == workerSingleTable.employmentDate && leaveDate == workerSingleTable.leaveDate && Objects.equals(position, workerSingleTable.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, employmentDate, leaveDate);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", birthDate=" + super.getBirthDate() +
                ", phoneNumber='" + super.getPhoneNumber() + '\'' +
                "position='" + position + '\'' +
                ", employmentDate=" + employmentDate +
                ", leaveDate=" + leaveDate +
                '}';
    }
}
