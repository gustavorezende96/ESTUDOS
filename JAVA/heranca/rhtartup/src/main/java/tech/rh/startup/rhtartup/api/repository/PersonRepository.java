package tech.rh.startup.rhtartup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rh.startup.rhtartup.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
