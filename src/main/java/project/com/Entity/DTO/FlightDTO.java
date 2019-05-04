package project.com.Entity.DTO;

import project.com.Entity.Crew;
import project.com.Entity.Flight;


public class FlightDTO {

    private Long id;

    private String name;

    private Long crewId;

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
