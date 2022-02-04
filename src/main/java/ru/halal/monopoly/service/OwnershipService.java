package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.ownerships.Ownership;

import java.util.Collection;

public interface OwnershipService {
    <T extends Ownership> Ownership createOrUpdate(T ownership);

    Collection<Ownership> getOwnerships(int limit);

    Ownership getOwnership(int id);

    Boolean delete(int id);

//    int ownershipToDeposit(Ownership ownership);
//
//    void changeToAnotherGamer(Ownership ownership, Gamer gamer2);
}
