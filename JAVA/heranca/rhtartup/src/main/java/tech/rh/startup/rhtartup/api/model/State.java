package tech.rh.startup.rhtartup.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tech.rh.startup.rhtartup.api.enums.DemographicDensity;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "State")
@Audited
public class State implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "demographic_density")
    private DemographicDensity demographicDensity;

    @Column(name = "uf")
    private String uf;

}
