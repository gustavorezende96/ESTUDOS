package tech.rh.startup.rhtartup.api.model;


import tech.rh.startup.rhtartup.api.enums.Gender;
import tech.rh.startup.rhtartup.api.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.annotations.DiscriminatorOptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@DiscriminatorValue("PERSON")
@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name = "_OBJECT_TYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonSubTypes({@JsonSubTypes.Type(value = WorkerPj.class, name = "WORKER"),
        @JsonSubTypes.Type(value = WorkerClt.class, name = "WORKER_CLT"),
        @JsonSubTypes.Type(value = WorkerPj.class, name = "WORKER_PJ"),
        @JsonSubTypes.Type(value = Dependent.class, name = "DEPENDENT")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "objectType", visible = true)
@Table(name = "PERSON")
@Audited
@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "_OBJECT_TYPE", insertable = false, updatable = false)
    @JsonIgnore
    private String objectType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_type")
    @Enumerated(value = EnumType.STRING)
    private PersonType type;

    @Column(name = "person_age")
    private Integer age;

    @Column(name = "person_gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "person")
    private List<Address> address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && getType() == person.getType() && Objects.equals(getAge(), person.getAge()) && getGender() == person.getGender() && Objects.equals(getAddress(), person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), getAge(), getGender(), getAddress());
    }
}
