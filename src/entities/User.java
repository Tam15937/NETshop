package entities;

import utils.AppData;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class User implements Serializable {

    private String login;
    private String password;
    private Basket basket;
    private HashMap<String, ArrayList<Product>> purchase;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
        this.purchase = new HashMap<>();
    }

    public void buyProduct(Category category, Product product) {
        if (this.purchase.containsKey(category.getName())) {
            this.purchase.get(category.getName()).add(product);
        } else {
            ArrayList<Product> productsArray = new ArrayList<>();
            productsArray.add(product);
            purchase.put(category.getName(), productsArray);
        }
        System.out.println("\nУспешно куплено!\n");
    }

    public void showPurchase() {
        if (this.purchase.size() != 0) {
            AppData.showTodayDate();
            int k = 1;
            System.out.format("\t %1$-30s  %2$-10s  %3$-10s\n","Название","Категория","Цена");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            for (String categoryName : purchase.keySet()) {
                ArrayList<Product> productsArray=purchase.get(categoryName);
                for (Product product : productsArray) {
                    System.out.println(k + ". " + product.toString(categoryName));
                    k++;
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("Нет покупок, сходите в магазин!");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void showBasket() {
        this.basket.show();
    }

    public void buyFromBasket(Category category, int number) {
        Product product = this.basket.getProducts().get(category.getName()).get(number);
        addToPurchase(category, product);
        this.basket.getProducts().get(category.getName()).remove(number);
    }

    private void addToPurchase(Category category, Product product) {
        if (this.purchase.containsKey(category.getName())) {
            this.purchase.get(category.getName()).add(product);
        } else {
            ArrayList<Product> productsArray = new ArrayList<>();
            productsArray.add(product);
            this.purchase.put(category.getName(), productsArray);
        }
    }

    public void buyAllFromBasket() {
        for (String categoryName : this.basket.getProducts().keySet()) {
            ArrayList<Product> productsArray = this.basket.getProducts().get(categoryName);
            if (this.purchase.containsKey(categoryName)) {
                this.purchase.get(categoryName).addAll(productsArray);
            } else {
                this.purchase.put(categoryName, productsArray);
            }
        }
    }

    public void addToBasket(Category category, Product product) {
        this.basket.add(category, product);
    }

    public int basketSize() {
        return this.basket.sizeOfProductsInBasket();
    }

    public void removeFromBasket(Category category, int number) {
        this.basket.remove(category, number);
    }

    public double purchasePrice() {
        double price = 0.0;
        for (String categoryName : this.purchase.keySet()) {
            ArrayList<Product> productsArray = this.purchase.get(categoryName);
            for (Product product : productsArray) {
                price += product.getPriсe();
            }
        }
        return price;
    }

    public void displayUserLoginAndPassword(){
        System.out.format("Логин:%1$-20s Пароль:%2$-20s\n",this.login,this.password);
    }



}
