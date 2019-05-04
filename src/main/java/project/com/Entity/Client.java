package project.com.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас-Entity для об'єкта клієнт
 * містить поля клієнта та гетери, сетери, контруктори
 */
@Entity
@Table(name = "клиент")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * Зв'язок багато до одного.
     * Означає, що клієнт може мати багато єкіпажів.
     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crew> crews = new ArrayList<>();

    /**
     * Зв'язок багато до одного.
     * Означає, що клієнт може мати багато транспортів.
     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transport> transports = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    public void addCrews(Crew crew) {
        this.crews.add(crew);
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public void addTransport(Transport transport) {
        this.transports.add(transport);
    }
}