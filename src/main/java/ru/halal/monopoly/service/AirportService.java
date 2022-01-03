package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.Communal;

public interface AirportService {
    Airport create(Airport airport);

    int airportToDeposit(Airport airport);

    void changeToAnotherGamer(Airport airport, Gamer gamer2);
}
