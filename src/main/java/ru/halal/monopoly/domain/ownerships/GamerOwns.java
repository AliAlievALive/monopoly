package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.Gamer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class GamerOwns {
    @Id
    @SequenceGenerator(
            name = "own_id_sequence",
            sequenceName = "own_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "own_id_sequence"
    )
    private int id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "gamer_id")
    private Gamer gamer;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @ToString.Exclude
//    @JsonManagedReference
    @JoinColumn(name = "city_id")
    private List<City> cities;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @ToString.Exclude
//    @JsonManagedReference
    @JoinColumn(name = "airport_id")
    private List<Airport> airports;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @ToString.Exclude
//    @JsonManagedReference
    @JoinColumn(name = "communal_id")
    private List<Communal> communals;

    public <T extends Ownership> void addOwn(T ownership) {
        if (ownership instanceof City) {
            if (cities == null) {
                cities = new ArrayList<>();
            }
            cities.add((City) ownership);
        } else if (ownership instanceof Airport) {
            if (airports == null) {
                airports = new ArrayList<>();
            }
            airports.add((Airport) ownership);
        } else if (ownership instanceof Communal) {
            if (communals == null) {
                communals = new ArrayList<>();
            }
            communals.add((Communal) ownership);
        }
    }

    public <T extends Ownership> Boolean removeOwn(T ownershipForRemove) {
        if (ownershipForRemove instanceof City) {
            return cities.remove((City) ownershipForRemove);
        } else if (ownershipForRemove instanceof Airport) {
            return airports.remove((Airport) ownershipForRemove);
        } else if (ownershipForRemove instanceof Communal) {
            return communals.remove((Communal) ownershipForRemove);
        }
        return FALSE;
    }
}
