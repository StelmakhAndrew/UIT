package project.com.Entity.DTO;

import project.com.Entity.*;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація патерну DTO.
 * Створений для передачі даних,
 * для зручності в серіалізації об'єкта "экіпаж" в формат Json
 * та уникнення зацикленості при поглибленій серіалізації.
 */
public class CrewDTO {

    private Long id;

    private List<Long> personsId;

    private List<Long> transportId;

    private Long flightId;

    private Long clientId;

    /**
     * @param crew
     * Конструктор для створення об'єкту CrewDTO з об'єкта Crew
     * з отриманням необхідних полів.
     */
    public CrewDTO(Crew crew) {
        this.id = crew.getId();
        this.personsId = crew.getPersons().stream().map(Person::getId).collect(Collectors.toList());
        this.transportId = crew.getTransport().stream().map(Transport::getId).collect(Collectors.toList());
        this.flightId = crew.getFlight()!=null?crew.getFlight().getId():null;
        this.clientId = crew.getClient()!=null?crew.getClient().getId():null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getPersonsId() {
        return personsId;
    }

    public void setPersonsId(List<Long> personsId) {
        this.personsId = personsId;
    }

    public List<Long> getTransportId() {
        return transportId;
    }

    public void setTransportId(List<Long> transportId) {
        this.transportId = transportId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
