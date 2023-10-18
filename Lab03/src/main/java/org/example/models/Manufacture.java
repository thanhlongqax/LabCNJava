package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private Integer employee;

    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phones;

    public Manufacture(String name, String location, Integer employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public Manufacture(String name, String location, Integer employee, List<Phone> phones) {
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.phones = phones;
    }

    public Manufacture() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        String phonesSize = this.phones == null ? "0" : String.valueOf(this.phones.size());
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee=" + employee +
                ", phoneLength=" + phonesSize +
                '}';
    }
}
