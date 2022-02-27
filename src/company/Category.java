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
        System.out.println("\nВоть Список товаров\n");
        System.out.format("\t|%1$-30s |%2$-10s |%3$-10s\n\n","Название","Цена","Рейтинг");
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(i + 1 + ". " + this.products.get(i));
        }
    }

}
