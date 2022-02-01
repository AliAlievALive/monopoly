package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.CanBuildHome;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City extends Ownership implements CanBuildHome {
    private EColors color;
    private int homeCost;
    private int homeCount;

    @Override
    public int addHome() {
        return ++homeCount;
    }

    @Override
    public int takeHome() {
        return --homeCount;
    }
}
