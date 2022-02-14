package company;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String login;
    private String password;
    private Basket basket;
    private ArrayList<Product> purchase;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
        this.purchase = new ArrayList<>();
    }

    public void byProduct(Product product) {
        this.purchase.add(product);
        System.out.println("\nУспешно куплено!\n");
    }

    public void showPurchase() {
        while (true) {
            int k = 1;
            for (Product product : purchase) {
                System.out.println(k + ". " + product.toString());
                k++;
            }
            System.out.print("\nНажмите любую клавишу --> ");
            Scanner in=new Scanner(System.in);
            String action=in.nextLine();
            return;
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

    public boolean checkBasketForProduct() {
        return this.basket.checkForProducts();
    }

    public boolean checkPurchaseForProduct() {
        return this.purchase.size() != 0;
    }

    public void byFromBasket(int number) {
        Product product = this.basket.remove(number);
        this.purchase.add(product);
    }

    public void addToBasket(Product product) {
        this.basket.add(product);
    }

    public int takeCountsOfProduktsFromBasket() {
        return this.basket.countProductsInBasket();
    }
}
