package co.develhope.customQueries01.repositories;

import co.develhope.customQueries01.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
