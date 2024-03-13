package cz.spsmb.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    Color color;

    @OneToMany(mappedBy = "id")
    List<Price> prices;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    Type type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color=" + color +
                ", price=" + prices +
                ", type=" + type +
                '}';
    }
}
