package project.com.Entity.DTO;

import project.com.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewDTO {

    private Long id;

    private List<Long> personsId = new ArrayList<>();

    private List<Long> transportId = new ArrayList<>();

    private Long flightId = null;

    private Long clientId = null;

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
