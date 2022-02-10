package company;
public class User {
    private String login;
    private String password;
    private Basket basket;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
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

    public boolean checkBasketForProduct(){
        return this.basket.checkForProducts();
    }

    public void removeFromBasket(int number) {
        this.basket.remove(number);
    }

    public void addToBasket(Product product) {
        this.basket.add(product);
    }
}
