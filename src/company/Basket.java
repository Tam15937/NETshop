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

    public Product remove(int number) {
        Product product=products.get(number);
        this.products.remove(number);
        return product;
    }
    public ArrayList<Product> removeAll(){
        ArrayList<Product> p=products;
        this.products=new ArrayList<>();
        return p;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean checkForProducts(){
        return this.products.size() == 0;
    }
    public int countProductsInBasket(){
        return products.size();
    }
}
