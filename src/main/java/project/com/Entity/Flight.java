package project.com.Entity;

import javax.persistence.*;

/**
 * Клас-Entity для об'єкта маршрут
 * містить поля маршруту та гетери, сетери, контруктори
 */
@Entity
@Table(name = "рейс")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * Зв'язок один до одного.
     * Означає, що екіпаж може мати тільки один маршрут.
     */
    @OneToOne(mappedBy = "flight",fetch = FetchType.LAZY)
    private Crew crew;

    public Flight() {
    }

    public Flight(String name) {
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

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}