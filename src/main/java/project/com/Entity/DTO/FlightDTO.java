package project.com.Entity.DTO;


import project.com.Entity.Flight;

/**
 * Реалізація патерну DTO.
 * Створений для передачі даних,
 * для зручності в серіалізації об'єкта "маршрут" в формат Json
 * та уникнення зацикленості при поглибленій серіалізації.
 */
public class FlightDTO {

    private Long id;

    private String name;

    private Long crewId;

    /**
     * @param flight
     * Конструктор для створення об'єкту FlightDTO з об'єкта Flight
     * з отриманням необхідних полів.
     */
    public FlightDTO(Flight flight) {
        this.id = flight.getId();
        this.name = flight.getName();
        this.crewId = flight.getCrew()!=null?flight.getCrew().getId():null;
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

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }
}
