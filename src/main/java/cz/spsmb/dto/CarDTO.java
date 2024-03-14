package cz.spsmb.dto;

public class CarDTO {
    String color;
    String type;
    String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "color='" + color + '\'' + ", type='" + type + '\'' + ", price=" + price + '}';
    }
}
