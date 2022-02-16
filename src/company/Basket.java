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
    public void removeAll(){
        this.products=new ArrayList<>();
    }
    public Product getByNumber(int number){
        return products.get(number);
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean isBasketEmpty(){
        return this.products.size() == 0;
    }
    public int sizeOfProductsInBasket(){
        return products.size();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
