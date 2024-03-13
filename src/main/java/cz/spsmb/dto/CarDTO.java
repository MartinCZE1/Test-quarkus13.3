package cz.spsmb.dto;

import cz.spsmb.model.Price;

import java.util.List;

public class CarDTO {
    String color;
    String type;
    int price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
