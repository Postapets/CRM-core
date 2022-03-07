package ru.sfedu.crm.lab5.model;

import ru.sfedu.crm.enums.Rate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Feedback")
@Table(schema = "lab5", name = "Feedback")
public class Feedback implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(unique = true)
    private Request request;
    private Rate rate;
    private String message;

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(request, feedback.request) && rate == feedback.rate && Objects.equals(message, feedback.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, request, rate, message);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", request=" + request +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                '}';
    }
}
