package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.ownerships.Communal;

import java.util.Collection;

public interface CommunalService {
    Communal createOrUpdate(Communal communal);

    Communal update(Communal communal);

    Collection<Communal> getCommunalList(int limit);

    Communal getCommunal(int id);

    Boolean delete(int id);

    int communalToDeposit(Communal communal);
}
