package company;
public class User {
    private String login;
    private String password;
    private Basket basket;
    private Basket purchase;

    private boolean userActionIsBasket;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
        this.purchase=new Basket();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void showBasket() {
        if(userActionIsBasket)
            this.basket.show();
        else
            this.purchase.show();
    }

    public boolean checkBasketForProduct(){
        if(userActionIsBasket)
            return this.basket.checkForProducts();
        else
            return this.purchase.checkForProducts();
    }

    public void removeFromBasket(int number) {
        if(userActionIsBasket)
            this.basket.remove(number);
        else
            this.purchase.remove(number);
    }

    public void addToBasket(Product product) {
        if(userActionIsBasket)
            this.basket.add(product);
        else
            this.purchase.add(product);
    }

    public int takeCountsOfProduktsFromBasket(){
        if(userActionIsBasket)
            return this.basket.countProductsInBasket();
        else
            return this.purchase.countProductsInBasket();

    }

    public void setUserActionIsBasket(boolean userActionIsBasket) {
        this.userActionIsBasket = userActionIsBasket;
    }
}
