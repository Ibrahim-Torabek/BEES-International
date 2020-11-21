package ibrahim.example.beesinernatinal;

public class ProductType {

    private String name;
    private String description;
    private int imageSource;

    public ProductType(String name, String description, int imageSource) {
        this.name = name;
        this.description = description;
        this.imageSource = imageSource;
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
