package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.GamerOwns;
import ru.halal.monopoly.domain.ownerships.Ownership;

import java.util.Collection;

public interface GamerService {
    Gamer create(Gamer gamer);

    Gamer update(Gamer gamer);

    int changeMoneyCounts(int id, int money);

    Boolean moneyToGamerFromGamerName(String name1, int money, String name2);

    Boolean addOwnToGamer(Ownership ownership, int gamerId);

    GamerOwns getOwns(Gamer gamer);

    int getMoney(String name);

    int getMoney(Gamer gamer);

    Collection<Gamer> getGamers(int limit);

    Gamer getGamerById(int id);

    Boolean delete(int id);

    Boolean giveOwnToAnotherGamer(int fromGamerId, int toGamerId, int cityId);
}
