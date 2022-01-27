package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

import java.util.Collection;
import java.util.List;

public interface GamerService {
    Gamer create(Gamer gamer);

    Gamer update(Gamer gamer);

    int changeMoneyCounts(int id, int money);

    Boolean addCityToGamer(int cityId, int gamerId);

    void addCommunalToGamer(Communal communal, int id);

    void addAirportToGamer(Airport airport, int id);

    List<City> getCities(Gamer gamer);

    List<Communal> getCommunal(Gamer gamer);

    List<Airport> getAirport(Gamer gamer);

    int getMoney(String name);

    int getMoney(Gamer gamer);

    Collection<Gamer> getGamers(int limit);

    Gamer getGamerById(int id);

    Boolean delete(int id);

    Boolean giveCityToAnotherGamer(int fromGamerId, int toGamerId, int cityId);
}
