package ru.halal.monopoly.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gamer {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private int money;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<City> cities;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Communal> communal;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Airport> airports;

    public void addCity(City city) {
        cities.add(city);
    }

    public void addCommunal(Communal communal) {
        this.communal.add(communal);
    }

    public void addAirport(Airport airport) {
        airports.add(airport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return id == gamer.id && money == gamer.money && Objects.equals(name, gamer.name) && Objects.equals(cities, gamer.cities) && Objects.equals(communal, gamer.communal) && Objects.equals(airports, gamer.airports);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
