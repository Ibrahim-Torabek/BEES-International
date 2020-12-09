package ibrahim.example.beesinernatinal.pojo;

import java.io.Serializable;

public class ProductType implements Serializable {

    private String name;
    private String description;
    private int imageSource;
    private double price;

    public ProductType(String name, String description, int imageSource,double price) {
        this.name = name;
        this.description = description;
        this.imageSource = imageSource;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return name;
    }
}
