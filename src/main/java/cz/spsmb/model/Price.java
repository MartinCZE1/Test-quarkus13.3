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
    int price;

    @OneToOne(mappedBy = "price")
    Car car;

    public Price(int price) {
        this.price = price;
    }

    public Price() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
