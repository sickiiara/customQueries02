package co.develhope.customQueries02.entities;

import java.util.Random;

public enum FlightStatusEnum {

    ONTIME,
    DELAYED,
    CANCELLED;

    private static final Random PRNG = new Random();
    public static FlightStatusEnum randomFlightStatus(){
        FlightStatusEnum[] flightStatuses = values();
        return flightStatuses[PRNG.nextInt(flightStatuses.length)];
    }

}
