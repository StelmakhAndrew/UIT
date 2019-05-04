package project.com.Entity.DTO;

import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;

    private String name;

    private List<Long> crewsId = new ArrayList<>();

    private List<Long> transportsId = new ArrayList<>();

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.crewsId = client.getCrews().stream().map(Crew::getId).collect(Collectors.toList());
        this.transportsId = client.getTransports().stream().map(Transport::getId).collect(Collectors.toList());;
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

    public List<Long> getCrewsId() {
        return crewsId;
    }

    public void setCrewsId(List<Long> crewsId) {
        this.crewsId = crewsId;
    }

    public List<Long> getTransportsId() {
        return transportsId;
    }

    public void setTransportsId(List<Long> transportsId) {
        this.transportsId = transportsId;
    }
}
