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
@DiscriminatorValue("WORKER_CLT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "objectType", visible = true)
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class WorkerClt extends Worker {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String OBJECT_TYPE = "WORKER_CLT";

    @Column
    private Boolean hasVacation;

    @Column
    private Double salary;

    @Column
    private Double commission;

    @Column
    private Double discount;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "WORKER_CLT_BENEFIT", joinColumns = @JoinColumn(name = "WORKER_CLT_ID"), inverseJoinColumns = @JoinColumn(name = "BENEFIT_ID"))
    private Set<Benefit> benefits;

    @ManyToOne
    @JoinColumn(name = "worker_pj_id")
    private WorkerPj workerPj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerClt workerClt)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getHasVacation(), workerClt.getHasVacation()) && Objects.equals(getSalary(), workerClt.getSalary()) && Objects.equals(getCommission(), workerClt.getCommission()) && Objects.equals(getDiscount(), workerClt.getDiscount()) && Objects.equals(getBenefits(), workerClt.getBenefits());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHasVacation(), getSalary(), getCommission(), getDiscount(), getBenefits());
    }
}
