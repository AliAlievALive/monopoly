package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.CommunalCostCalc;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Communal extends Ownership implements CommunalCostCalc {
    private int multiplier = 4;
    @Override
    public int increasePay() {
        return multiplier = 10;
    }

    @Override
    public int decreasePay() {
        return multiplier = 4;
    }
}
