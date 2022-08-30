package co.develhope.customQueries02.controllers;

import co.develhope.customQueries02.entities.Flight;
import co.develhope.customQueries02.entities.FlightStatusEnum;
import co.develhope.customQueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static co.develhope.customQueries02.entities.FlightStatusEnum.ONTIME;
import static co.develhope.customQueries02.entities.FlightStatusEnum.randomFlightStatus;

@RestController
@RequestMapping("/flights")
public class FlightController {

    public String generateRandomValue(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @Autowired
    private FlightRepository flightRepository;

    //provisioning of n flights (n is an optional query param; if absent, n = 100)
    @GetMapping("/provisioning")
    public void provisionFlights(@RequestParam(required = false) Integer n){
        if(n == null) n=100;
        List<Flight> newFlights = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Flight flight = new Flight();
            flight.setDescription(generateRandomValue());
            flight.setFromAirport(generateRandomValue());
            flight.setToAirport(generateRandomValue());
            flight.setFlightStatus(FlightStatusEnum.randomFlightStatus());
            newFlights.add(flight);
        }
        flightRepository.saveAll(newFlights);
    }

    @GetMapping("")
    public Page<Flight> getAllFlights(@RequestParam int page, @RequestParam int size){
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    @GetMapping("/status")
    public List<Flight> getAllFlightsByStatus(){
        return flightRepository.findByFlightStatus(ONTIME);
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlight(@RequestParam FlightStatusEnum p1, @RequestParam FlightStatusEnum p2){
        return flightRepository.getCustomFlight(p1, p2);
    }

}
