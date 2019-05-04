package project.com.Entity.DTO;

import project.com.Entity.Person;

public class PersonDTO {

    private Long id;

    private String firstName;

    private String secondName;

    private Long crewId;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.secondName = person.getSecondName();
        this.crewId = person.getCrew()!=null? person.getCrew().getId():null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }
}
