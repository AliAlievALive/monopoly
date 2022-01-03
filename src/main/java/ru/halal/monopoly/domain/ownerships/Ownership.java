package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.Gamer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Ownership {
    private int id;
    private String name;
    private int cost;
    private int rentCost;
    private int deposit;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    private Gamer gamer;
}
