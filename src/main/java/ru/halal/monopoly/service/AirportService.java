package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.ownerships.Airport;

import java.util.Collection;

public interface AirportService {
    Airport create(Airport airport);

    Airport update(Airport airport);

    Collection<Airport> getAirportList(int limit);

    Airport getAirport(int id);

    Boolean delete(int id);

    int airportToDeposit(Airport airport);

}
