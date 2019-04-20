package project.com.Entity;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;


    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "currency")
    private String currency;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "version")
    private Integer version;

    @ManyToOne
    @JoinTable(name = "relation",joinColumns = @JoinColumn(name = "CHILD_COMPONENT"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_COMPONENT"))
    private DocumentPackage parent_component;

    public Document() {
    }

    public Document(String accountNumber, String currency, String productType, Integer version) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.productType = productType;
        this.version = version;
    }

    public Document getNewVersion(){
        Document newDocument = new Document();
        newDocument.accountNumber = this.accountNumber;
        newDocument.currency = this.currency;
        newDocument.productType = this.productType;
        newDocument.version = this.version + 1;
        return newDocument;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public DocumentPackage getParent_component() {
        return parent_component;
    }

    public void setParent_component(DocumentPackage parent_component) {
        this.parent_component = parent_component;
    }
}