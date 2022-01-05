package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;

import java.util.Collection;
import java.util.List;

public interface CityService {
    City create(City city);

    City update(City city);

    int cityToDeposit(City city);

    void addHome(City city, int money);

    int soldHome(City city);

    void changeToAnotherGamer(City city, Gamer gamer2);

    Collection<City> getCities(int limit);

    City getCity(int id);

    Boolean delete(int id);
}
