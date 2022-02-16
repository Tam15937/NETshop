package company;

import java.util.ArrayList;
public class Category {


    private String name;
    private ArrayList<Product> products;

    public Category(String name, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showProducts(){
        System.out.println("\nВоть Список товаров");
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(i + 1 + ". " + this.products.get(i));
        }
    }

}
