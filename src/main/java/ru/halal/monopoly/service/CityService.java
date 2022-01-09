package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;

import java.util.Collection;

public interface CityService {
    City create(City city);

    Collection<City> getCities(int limit);

    City getCity(int id);

    City update(City city);

    Boolean delete(int id);

    int cityToDeposit(int id);

    Boolean cityFromDeposit(int id);

    void addHome(City city, int money);

    int soldHome(City city);

    void changeToAnotherGamer(City city, Gamer gamer2);
}
