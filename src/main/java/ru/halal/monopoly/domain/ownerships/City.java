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
    private int oneCityCost;
    private int twoCityCost;
    private int threeCityCost;
    private int payToStayOnCity = 0;

    @Override
    public int addHome() {
        return ++homeCount;
    }

    @Override
    public int takeHome() {
        return --homeCount;
    }

    @Override
    public int increasePay() {
        if (homeCount == 1) {
            return oneCityCost;
        } else if (homeCount == 2) {
            return twoCityCost;
        } else {
            return threeCityCost;
        }
    }

    @Override
    public int decreasePay() {
        return 0;
    }
}
