package project.com.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "экипаж")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons = new ArrayList<>();

    @OneToMany(mappedBy = "crew" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transport> transport = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Transport> getTransport() {
        return transport;
    }

    public void setTransport(List<Transport> transport) {
        this.transport = transport;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}