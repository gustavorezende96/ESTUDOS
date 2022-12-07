package tech.rh.startup.rhtartup.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tech.rh.startup.rhtartup.api.enums.DependentType;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@DiscriminatorValue("DEPENDENT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType", visible = true)
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class Dependent extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String OBJECT_TYPE = "DEPENDENT";

    @Column
    @Enumerated(value = EnumType.STRING)
    private DependentType dependentType;

    @ManyToOne
    @JoinColumn(name = "WORKER_ID", referencedColumnName = "ID")
    private Worker worker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependent dependent)) return false;
        if (!super.equals(o)) return false;
        return getDependentType() == dependent.getDependentType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDependentType());
    }
}
