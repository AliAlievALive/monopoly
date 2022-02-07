package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.halal.monopoly.domain.GamerOwns;
import ru.halal.monopoly.domain.ownerships.interfaces.AirportCostCalc;
import ru.halal.monopoly.domain.ownerships.interfaces.CanBeInDeposit;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Airport implements AirportCostCalc, CanBeInDeposit {
    @Id
    @SequenceGenerator(name = "airport_id_sequence", sequenceName = "airport_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_id_sequence")
    @Column(name = "id", insertable = false, updatable = false)
    private int id;
    private String name;
    private int cost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    private int rentCost = 250;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "own_id")
    @JsonBackReference
    private GamerOwns gamerOwns;

    @Override
    public int increasePay() {
        return rentCost *= 2;
    }

    @Override
    public int decreasePay() {
        return rentCost /= 2;
    }
}
