package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CoffeHouses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "houseType")
    private String houseType;
    @Column(name = "cuisineType")
    private String cuisineType;
    @Column(name = "workingHours")
    private int workingHours;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "advantages")
    private String advantages;

    public CoffeHouses() {
    }

    public CoffeHouses(String title, String houseType, String cuisineType, int workingHours, String description, String location, String advantages) {
        this.title = title;
        this.houseType = houseType;
        this.cuisineType = cuisineType;
        this.workingHours = workingHours;
        this.description = description;
        this.location = location;
        this.advantages = advantages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeHouses that = (CoffeHouses) o;
        return id == that.id &&
                workingHours == that.workingHours &&
                Objects.equals(title, that.title) &&
                Objects.equals(houseType, that.houseType) &&
                Objects.equals(cuisineType, that.cuisineType) &&
                Objects.equals(description, that.description) &&
                Objects.equals(location, that.location) &&
                Objects.equals(advantages, that.advantages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, houseType, cuisineType, workingHours, description, location, advantages);
    }

    @Override
    public String toString() {
        return "CoffeHouses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", houseType='" + houseType + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", workingHours=" + workingHours +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", advantages='" + advantages + '\'' +
                '}';
    }
}
