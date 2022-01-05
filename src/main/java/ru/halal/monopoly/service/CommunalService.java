package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

import java.util.Collection;

public interface CommunalService {
    Communal create(Communal communal);

    Communal update(Communal city);

    Collection<Communal> getCommunalList(int limit);

    Communal getCommunal(int id);

    Boolean delete(int id);

    int communalToDeposit(Communal communal);

    void changeToAnotherGamer(Communal communal, Gamer gamer2);
}
