package ru.sfedu.crm.lab3.model.MappedSuperclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class Worker
 */
@Entity(name = "WorkerMappedSuperclass")
@Table(schema = "lab3_msc")
public class WorkerMappedSuperclass extends UserMappedSuperclass {
    private String position;
    private long employmentDate;
    private long leaveDate;

    public WorkerMappedSuperclass() {}

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
        WorkerMappedSuperclass WorkerMappedSuperclass = (WorkerMappedSuperclass) o;
        return employmentDate == WorkerMappedSuperclass.employmentDate && leaveDate == WorkerMappedSuperclass.leaveDate && Objects.equals(position, WorkerMappedSuperclass.position);
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
