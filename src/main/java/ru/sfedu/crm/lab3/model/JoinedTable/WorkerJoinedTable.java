package ru.sfedu.crm.lab3.model.JoinedTable;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Worker
 */

@Entity(name = "WorkerJoinedTable")
@Table(schema = "lab3_jt", name ="Worker_Joined_Table" )
@PrimaryKeyJoinColumn(name = "user_id")
public class WorkerJoinedTable extends UserJoinedTable {
    private String position;
    private long employmentDate;
    private long leaveDate;

    public WorkerJoinedTable() {}

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
        WorkerJoinedTable workerJoinedTable = (WorkerJoinedTable) o;
        return employmentDate == workerJoinedTable.employmentDate && leaveDate == workerJoinedTable.leaveDate && Objects.equals(position, workerJoinedTable.position);
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
