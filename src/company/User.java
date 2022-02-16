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
            int k = 1;
            for (Product product : purchase) {
                System.out.println(k + ". " + product.toString());
                k++;
            }
            System.out.print("\nНажмите Enter, что бы продолжить... ");
            Scanner in=new Scanner(System.in);
            String action=in.nextLine();
        }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void showBasket() {
        if (this.basket.lookingForProductsInBasket()) {
            System.out.println("\nНет товаров в корзине.\n");
        }
        else this.basket.show();
    }

    public boolean lookingForProductsInPurchases() {
        return this.purchase.size() != 0;
    }

    public void byFromBasket(int number) {
        this.purchase.add(this.basket.getByNumber(number));
        this.basket.remove(number);
    }

    public void byAllFromBasket(){
        this.purchase.addAll(this.basket.getProducts());
        this.basket.removeAll();
    }

    public void addToBasket(Product product) {
        this.basket.add(product);
    }

    public int takeCountsOfProduktsFromBasket() {
        return this.basket.countProductsInBasket();
    }

    public void removeFromBasket(int number){
        this.basket.remove(number);
    }
}
