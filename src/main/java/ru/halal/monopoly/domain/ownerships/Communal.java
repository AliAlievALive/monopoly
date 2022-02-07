package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.halal.monopoly.domain.GamerOwns;
import ru.halal.monopoly.domain.ownerships.interfaces.CommunalCostCalc;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Communal implements CommunalCostCalc {
    @Id
    @SequenceGenerator(name = "communal_id_sequence", sequenceName = "communal_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communal_id_sequence")
    @Column(name = "id", insertable = false, updatable = false)
    private int id;
    private String name;
    private int cost;
    private int rentCost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    private int multiplier = 4;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "own_id")
    @JsonBackReference
    private GamerOwns gamerOwns;

    @Override
    public int increasePay() {
        return multiplier = 10;
    }

    @Override
    public int decreasePay() {
        return multiplier = 4;
    }
}
