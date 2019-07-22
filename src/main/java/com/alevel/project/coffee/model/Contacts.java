package com.alevel.project.coffee.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "website")
    private String website;

    public Contacts() {
    }

    public Contacts(String address, String phone, String website) {
        this.address = address;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return id == contacts.id &&
                Objects.equals(address, contacts.address) &&
                Objects.equals(phone, contacts.phone) &&
                Objects.equals(website, contacts.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone, website);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
