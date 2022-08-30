package co.develhope.customQueries02.repositories;

import co.develhope.customQueries02.entities.Flight;
import co.develhope.customQueries02.entities.FlightStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightStatus(FlightStatusEnum flightStatus);
    @Query("select f from Flight f where f.flightStatus = :p1 OR f.flightStatus = :p2")
    List<Flight> getCustomFlight(@Param("p1") FlightStatusEnum p1, @Param("p2") FlightStatusEnum p2);

}
