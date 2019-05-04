package project.com.Entity.DTO;

import project.com.Entity.Transport;

/**
 * Реалізація патерну DTO.
 * Створений для передачі даних,
 * для зручності в серіалізації об'єкта "транспорт" в формат Json
 * та уникнення зацикленості при поглибленій серіалізації.
 */
public class TransportDTO {

    private Long id;

    private Long crewId;

    private Long clientId;

    private String model;

    private Integer productionYear;

    private Integer mileage;

    private String photoUrl;

    /**
     * @param transport
     * Конструктор для створення об'єкту TransportDTO з об'єкта Transport
     * з отриманням необхідних полів.
     */
    public TransportDTO(Transport transport) {
        this.id = transport.getId();
        this.crewId = transport.getCrew()!=null?transport.getCrew().getId():null;
        this.clientId = transport.getClient()!=null?transport.getClient().getId():null;
        this.model = transport.getModel();
        this.productionYear = transport.getProductionYear();
        this.mileage = transport.getMileage();
        this.photoUrl = transport.getPhotoUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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
}
