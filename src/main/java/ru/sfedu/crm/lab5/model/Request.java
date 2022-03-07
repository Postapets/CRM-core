package ru.sfedu.crm.lab5.model;

import ru.sfedu.crm.enums.RequestStatus;
import ru.sfedu.crm.lab5.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Request")
@Table(schema = "lab5", name = "Request")
public class Request implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;
    private RequestStatus status;
    private String description;

    public Request() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(id, request.id) && Objects.equals(date, request.date) && Objects.equals(user, request.user) && status == request.status && Objects.equals(description, request.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, user, status, description);
    }

    @Override
    public String toString() {
        String str = "Request{" +
                "id=" + id +
                ", date=" + date;
        try {
            str += ", user=" + user.getId();
        } catch (Exception e) {
            str +=",user=null";
        }
        str+= ", status=" + status +
                ", description='" + description + '\'' +
                '}';
        return str;
    }
}
