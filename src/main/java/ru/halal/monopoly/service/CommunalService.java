package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

public interface CommunalService {
    Communal create(Communal communal);

    int communalToDeposit(Communal communal);

    void changeToAnotherGamer(Communal communal, Gamer gamer2);
}
