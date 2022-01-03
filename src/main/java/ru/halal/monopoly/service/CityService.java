package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;

public interface CityService {
    City create(City city);

    int cityToDeposit(City city);

    void addHome(City city, int money);

    int soldHome(City city);

    void changeToAnotherGamer(City city, Gamer gamer2);
}
