package tech.rh.startup.rhtartup.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tech.rh.startup.rhtartup.api.enums.CityRegion;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "City")
public class City implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_population")
    private Long cityPopulation;

    @Column(name = "city_region")
    @Enumerated(value = EnumType.STRING)
    private CityRegion cityRegion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
