package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GamerService {
    Gamer create(Gamer gamer);

    void update(Gamer gamer);

    void changeMoneyCounts(int id, int money);

    void addCityToGamer(City city, int id);

    void addCommunalToGamer(Communal communal, int id);

    void addAirportToGamer(Airport airport, int id);

    List<City> getCities(Gamer gamer);

    List<Communal> getCommunal(Gamer gamer);

    List<Airport> getAirport(Gamer gamer);

    int getMoney(String name);

    int getMoney(Gamer gamer);

    Collection<Gamer> getGamers(int limit);

    Gamer getGamer(int id);

    void delete(Gamer gamer);
}
