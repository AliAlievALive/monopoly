package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.ownerships.interfaces.CanSale;
import ru.halal.monopoly.domain.ownerships.interfaces.CostCalculate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public abstract class Ownership implements CanSale, CostCalculate {
    private String name;
    private int cost;
    private int rentCost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;

    @Override
    public int sale() {
        return cost;
    }
}
