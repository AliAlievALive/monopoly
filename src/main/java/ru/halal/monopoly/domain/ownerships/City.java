package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.halal.monopoly.domain.GamerOwns;
import ru.halal.monopoly.domain.ownerships.interfaces.CanBuildHome;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City implements CanBuildHome {
    @Id
    @SequenceGenerator(name = "city_id_sequence", sequenceName = "city_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_sequence")
    private int id;
    private String name;
    private EColors color;
    private int homeCost;
    private int homeCount;
    private int oneCityCost;
    private int twoCityCost;
    private int threeCityCost;
    private int payToStayOnCity = 0;
    private int cost;
    private int rentCost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "own_id")
    @JsonBackReference
    private GamerOwns gamerOwns;

    @Override
    public int addHome() {
        return ++homeCount;
    }

    @Override
    public int takeHome() {
        return --homeCount;
    }
}
