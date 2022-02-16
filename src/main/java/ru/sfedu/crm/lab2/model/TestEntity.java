package ru.sfedu.crm.lab2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Test_Entity")
public class TestEntity implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Column
    private Boolean control;
    @Embedded
    private EmbeddedEntity embeddedEntity;

    public TestEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getControl() {
        return control;
    }

    public void setControl(Boolean control) {
        this.control = control;
    }

    public EmbeddedEntity getEmbeddedEntity() {
        return embeddedEntity;
    }

    public void setEmbeddedEntity(EmbeddedEntity embeddedEntity) {
        this.embeddedEntity = embeddedEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity entity = (TestEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(name, entity.name) && Objects.equals(description, entity.description) && Objects.equals(dateCreated, entity.dateCreated) && Objects.equals(control, entity.control) && Objects.equals(embeddedEntity, entity.embeddedEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateCreated, control, embeddedEntity);
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", control=" + control +
                ", embeddedEntity=" + embeddedEntity +
                '}';
    }
}
