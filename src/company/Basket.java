package company;

import java.util.ArrayList;

public class Basket {

    private ArrayList<Product> products;

    public Basket() {
        this.products = new ArrayList<>();
    }

    public void show() {
        int k = 1;
        for (Product product : products) {
            System.out.println(k + ". " + product);
            k++;
        }
    }

    public void remove(int number) {
        this.products.remove(number);
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean checkForProducts(){
        if (products.size() != 0) {
            return true;
        } else
            return false;
    }
}
