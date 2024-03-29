package cz.spsmb.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "price")
public class Price implements Serializable {
    @Id
    @Column(name = "price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String price;

    @OneToOne
    @JoinColumn(name = "car_id")
    Car car;

    public Price(String price) {
        this.price = price;
    }

    public Price() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
