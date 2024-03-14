package cz.spsmb.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "color")
public class Color implements Serializable {
    public Color(String color) {
        this.color = color;
    }

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String color;

    @OneToMany(mappedBy = "color", fetch = FetchType.EAGER)
    List<Car> carList = new ArrayList<>();

    public Color() {
    }

    public Color(long id, String color, List<Car> carList) {
        this.id = id;
        this.color = color;
        this.carList = carList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
