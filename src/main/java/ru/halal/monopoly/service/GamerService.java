package ru.halal.monopoly.service;

import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.GamerOwns;
import ru.halal.monopoly.domain.ownerships.TypeWrapper;

import java.util.Collection;

public interface GamerService {
    Gamer create(Gamer gamer);

    Gamer update(Gamer gamer);

    int changeMoneyCounts(int id, int money);

    Boolean moneyToGamerFromGamerName(String name1, int money, String name2);

    Boolean addOwnToGamer(int cityId, int gamerId, TypeWrapper type);

    GamerOwns getOwn(Gamer gamer);

    int getMoney(String name);

    int getMoney(Gamer gamer);

    Collection<Gamer> getGamers(int limit);

    Gamer getGamerById(int id);

    Boolean delete(int id);

    Boolean giveOwnToAnotherGamer(TypeWrapper type, int fromGamerId, int toGamerId, int cityId);
}
