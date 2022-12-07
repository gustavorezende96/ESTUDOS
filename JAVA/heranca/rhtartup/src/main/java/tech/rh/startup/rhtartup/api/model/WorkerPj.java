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

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@DiscriminatorValue("WORKER_PJ")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "objectType", visible = true)
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class WorkerPj extends Worker {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String OBJECT_TYPE = "WORKER_PJ";

    @Column
    private Integer contractCount;

    @Column
    private Boolean hasCharter;

    @Column
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workerPj")
    private Set<WorkerClt> functionaries;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerPj workerPj)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getContractCount(), workerPj.getContractCount()) && Objects.equals(getHasCharter(), workerPj.getHasCharter()) && Objects.equals(getCnpj(), workerPj.getCnpj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContractCount(), getHasCharter(), getCnpj());
    }
}
