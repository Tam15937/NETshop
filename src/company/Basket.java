package company;

import java.util.ArrayList;
import java.util.HashMap;

public class Basket {

    private HashMap<String, ArrayList<Product>> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public void show() {
        int k = 1;
        System.out.format("\t|%1$-30s |%2$-10s |%3$-10s\n","Название","Цена","Рейтинг");
        for (ArrayList<Product> productsArray : products.values()) {
            for (Product product : productsArray) {
                System.out.println(k + ". " + product);
                k++;
            }
        }
    }

    public void remove(Category category, int number) {
        this.products.get(category.getName()).remove(number);
    }

    public void removeAll() {
        this.products = new HashMap<>();
    }

    public Product getByNumber(Category category, int number) {
        return products.get(category.getName()).get(number);
    }

    public void add(Category category, Product product) {
        if (this.products.containsKey(category.getName())) {
            this.products.get(category.getName()).add(product);
        } else {
            ArrayList<Product>productsArray=new ArrayList<>();
            productsArray.add(product);
            this.products.put(category.getName(), productsArray);
        }
    }

    public boolean isBasketEmpty() {
        return this.products.size() == 0;
    }

    public int sizeOfProductsInBasket() {
        int size=0;
        for(String category:this.products.keySet()){
            size+=this.products.get(category).size();
        }
        return size;
    }

    public HashMap<String, ArrayList<Product>> getProducts() {
        return products;
    }
}
