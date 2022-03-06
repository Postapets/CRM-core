package ru.sfedu.crm.lab5.model.one_to_one;

import ru.sfedu.crm.enums.RequestStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Request")
@Table(schema = "lab5_one_to_one", name = "Request")
public class Request {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long date;
    private RequestStatus status;
    private String description;

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(date, request.date) && status == request.status && Objects.equals(description, request.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, status, description);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
