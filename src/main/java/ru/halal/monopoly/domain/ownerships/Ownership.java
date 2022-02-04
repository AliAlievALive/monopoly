package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.CanSale;
import ru.halal.monopoly.domain.ownerships.interfaces.CostCalculate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ownership implements CanSale, CostCalculate {
    int id;
    String name;
    int cost;
    int rentCost;
    int depositCost;
    boolean isEnableToBuy;
    boolean isInDeposit;
    EType type;

    @Override
    public int sale() {
        return cost;
    }

    @Override
    public int increasePay() {
        return 0;
    }

    @Override
    public int decreasePay() {
        return 0;
    }
}
