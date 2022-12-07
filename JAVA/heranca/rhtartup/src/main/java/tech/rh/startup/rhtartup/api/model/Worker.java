package tech.rh.startup.rhtartup.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@DiscriminatorValue("WORKER")
@JsonSubTypes ({@JsonSubTypes.Type(value = WorkerPj.class, name = "WORKER_PJ"), @JsonSubTypes.Type(value = WorkerClt.class, name = "WORKER_CLT")})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "objectType", visible = true)
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
public abstract class Worker extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String OBJECT_TYPE = "WORKER";

    @Column
    private String jobName;

    @OneToMany(mappedBy = "worker")
    private Set<Dependent> dependentList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker worker)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getJobName(), worker.getJobName()) && Objects.equals(getDependentList(), worker.getDependentList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getJobName(), getDependentList());
    }
}
