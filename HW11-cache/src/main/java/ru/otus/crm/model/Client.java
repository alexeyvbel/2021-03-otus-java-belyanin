package ru.otus.crm.model;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Address.class, fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    @OneToMany(targetEntity = Phone.class, fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "client")
    private List<Phone> phones = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
        this.phones = new ArrayList<>();
        this.address = null;
    }

    public Client(String name, Address address, List<Phone> phones) {
        this.id = null;
        this.name = name;
        this.address = address;
        this.phones = phones;
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
    }

    public Client(String name, List<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Client(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Client clone() {
        Client clientCloned = new Client(this.id, this.name);
        if (this.address != null){
            Address newAddress = this.address.clone();
            clientCloned.setAddress(newAddress);
        }
        List<Phone> clonedPhones = phones.stream().map(p -> {
            Phone clonedPhone = p.clone();
            clonedPhone.setClient(clientCloned);
            return clonedPhone;
        }).collect(Collectors.toList());
        clientCloned.setPhones(clonedPhones);

        return clientCloned;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
