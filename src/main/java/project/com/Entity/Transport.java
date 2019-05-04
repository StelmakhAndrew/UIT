package project.com.Entity;

import javax.persistence.*;

/**
 * Клас-Entity для об'єкта транспорт
 * містить поля транспорту та гетери, сетери, контруктори
 */
@Entity
@Table(name = "транспорт")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Зв'язок багато до одного.
     * Означає, що декілька транспортів можуть бути в одному екіпажі.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Crew crew;

    /**
     * Зв'язок багато до одного.
     * Означає, що декілька транспортів можуть бути в одного клієнта.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "model")
    private String model;

    @Column(name = "productionYear")
    private Integer productionYear;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "photoUrl")
    private String photoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}