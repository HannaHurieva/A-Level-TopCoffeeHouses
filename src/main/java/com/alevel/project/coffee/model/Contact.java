package com.alevel.project.coffee.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true, nullable = false)
    private int id;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be empty")
    private String address;

    // The district of the city or the nearest metro station
    @Column(name = "location")
    private String location;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_coffee_house_id", referencedColumnName = "coffee_house_id")
    private CoffeeHouse coffeeHouse;

    public Contact() {
    }

    public Contact(String address, String phone, String website) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public CoffeeHouse getCoffeeHouse() {
        return coffeeHouse;
    }

    public void setCoffeeHouse(CoffeeHouse coffeeHouse) {
        coffeeHouse.setContact(this);
        this.coffeeHouse = coffeeHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (!address.equals(contact.address)) return false;
        if (location != null ? !location.equals(contact.location) : contact.location != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (website != null ? !website.equals(contact.website) : contact.website != null) return false;
        return coffeeHouse.equals(contact.coffeeHouse);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + address.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + coffeeHouse.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", coffeeHouse=" + coffeeHouse +
                '}';
    }
}
