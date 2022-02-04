package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.AirportCostCalc;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Airport extends Ownership implements AirportCostCalc {
    private int cost = 250;

    @Override
    public int increasePay() {
        return cost *= 2;
    }

    @Override
    public int decreasePay() {
        return cost /= 2;
    }
}
