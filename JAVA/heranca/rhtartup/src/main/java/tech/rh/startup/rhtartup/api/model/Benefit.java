package tech.rh.startup.rhtartup.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Getter
@Entity
@Table(name = "BENEFIT")
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class Benefit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "benefit_name")
    private String benefitName;

    @Column(name = "insurance")
    private Boolean isInsurance;

    @Column(name = "benefit_value")
    private Double benefitValue;
}
