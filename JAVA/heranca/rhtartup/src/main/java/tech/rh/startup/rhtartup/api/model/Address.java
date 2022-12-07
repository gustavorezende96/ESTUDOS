package tech.rh.startup.rhtartup.api.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import tech.rh.startup.rhtartup.api.enums.AddressType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")
@Audited
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column
    private String addressName;

    @Column
    private String addressNumber;

    @Column
    private String zipCode;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
