package ru.halal.monopoly.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class GamerOwns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "ownership", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    @ToString.Exclude
    private Gamer gamer;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "gamerOwns",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<City> cities;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "gamerOwns",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<Airport> airports;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "gamerOwns",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<Communal> communals;

    public void addCityToGamer(City city) {
        if (this.cities == null) {
            this.cities = new ArrayList<>();
        }
        this.cities.add(city);
        city.setGamerOwns(this);
    }

    public void addAirportToGamer(Airport airport) {
        if (this.airports == null) {
            this.airports = new ArrayList<>();
        }
        this.airports.add(airport);
        airport.setGamerOwns(this);
    }

    public void addCommunalToGamer(Communal communal) {
        if (this.communals == null) {
            this.communals = new ArrayList<>();
        }
        this.communals.add(communal);
        communal.setGamerOwns(this);
    }
}
