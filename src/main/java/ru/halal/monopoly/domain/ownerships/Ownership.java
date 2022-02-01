package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.interfaces.CanSale;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public abstract class Ownership implements CanSale {
    @Id
    @SequenceGenerator(
            name = "owner_id_sequence",
            sequenceName = "owner_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_id_sequence"
    )
    private int id;
    private String name;
    private int cost;
    private int rentCost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "gamer_id")
    @JsonBackReference
    private Gamer gamer;


}
