package ibrahim.example.beesinernatinal.pojo;

import java.util.ArrayList;

/**
 * <h1>App for BEES International</h1>
 * <h2>Android Project of MAD305 Course</h2>
 * This class is Category class
 *
 * @author Wusiman Yibuulayin
 * @version 1.0
 * @since 2020-11-20
 */
public class CategoryType {
    private String name;
    private int items;
    private int iconResource;// = R.drawable.ic_handtools;
    private ArrayList<ProductType> products = new ArrayList<>();;


    public CategoryType(String name, int iconResource) {
        this.name = name;
        this.iconResource = iconResource;
        //products = new ArrayList<>();
    }

    public CategoryType(String name) {
        this.name = name;
        items = 0;
        //products = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public ArrayList<ProductType> getProducts() {
        return products;
    }

    public void setProduct(ProductType product) {
        this.products.add(product);
        items++;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    @Override
    public String toString() {
        return name;
    }
}
