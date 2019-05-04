package project.com.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас-Entity для об'єкта екіпаж
 * містить поля екіпажу та гетери, сетери, контруктори
 */
@Entity
@Table(name = "экипаж")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Зв'язок один до багатьох.
     * Означає, що екіпаж може мати багато персон.
     */
    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons = new ArrayList<>();

    /**
     * Зв'язок один до багатьох.
     * Означає, що екіпаж може володіти багатьма транспортами.
     */
    @OneToMany(mappedBy = "crew" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transport> transport = new ArrayList<>();

    /**
     * Зв'язок один до одного.
     * Означає, що екіпаж може мати тільки один маршрут.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Flight flight;

    /**
     * Зв'язок багато до одного.
     * Означає, що у декількох екіпажів може бути один і той же клієнт.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
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

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public List<Transport> getTransport() {
        return transport;
    }

    public void setTransport(List<Transport> transport) {
        this.transport = transport;
    }

    public void addTransport(Transport transport) {
        this.transport.add(transport);
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}