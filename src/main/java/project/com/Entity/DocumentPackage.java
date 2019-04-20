package project.com.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "documentpackage")
public class DocumentPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "document_title")
    private String documentTitle;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "inn")
    private String inn;

    @Column(name = "passport")
    private String passport;

    @Column(name = "version")
    private Integer version;

    @OneToMany(mappedBy = "parent_component")
    private List<Document> child_component = new ArrayList<>();

    public DocumentPackage(){

    }

    public DocumentPackage(String documentTitle, String clientName, String inn, String passport, Integer version) {
        this.documentTitle = documentTitle;
        this.clientName = clientName;
        this.inn = inn;
        this.passport = passport;
        this.version = version;
    }

    public DocumentPackage getNewVersion(String clientName, String passport){
        DocumentPackage newDocumentPackage = new DocumentPackage();
        newDocumentPackage.clientName = clientName;
        newDocumentPackage.documentTitle = this.documentTitle;
        newDocumentPackage.inn = this.inn;
        newDocumentPackage.passport = passport;
        newDocumentPackage.version = this.version + 1;

        return newDocumentPackage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Document> getChild_component() {
        return child_component;
    }

    public void setChild_component(List<Document> child_component) {
        this.child_component = child_component;
    }


    public void addChild_component(Document child_component) {
        this.child_component.add(child_component);
    }
}