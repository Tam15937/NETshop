package company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    public Menu() {
        AppData.initializeForStart();
    }

    public void authorizationMenu() {
        while (true) {
            int action = HelpForUser.tryToRead(0, 2, HelpForUser.stringMenu);
            switch (action) {
                case 1:
                    User user = login();
                    if (user != null)
                        if (checkPassword(user)) {
                            categoryMenu(user);
                        }
                    break;

                case 2:
//                        User user
                case 0:
                    System.exit(0);
            }

        }
    }


    public void categoryMenu(User user) {
        while (true) {
            AppData.showCategories();
            ArrayList<Category> categories = AppData.categories;

            int action = HelpForUser.tryToRead(0, categories.size(), HelpForUser.stringCategoryMenu);

            if (action != 0) {
                Category category = categories.get(action - 1);
                productsMenu(user, category);
            } else return;

        }
    }

    public void productsMenu(User user, Category category) {


        while (true) {

            category.showProducts();
            ArrayList<Product> products = category.getProducts();

            int action = HelpForUser.tryToRead(-2, products.size(), HelpForUser.stringProductsMenu);
            if (action > 0) {
                actionOfProductMenu(user, products.get(action - 1));
            } else if (action == 0) {
                return;
            } else if (action == -1) {
                basketMenu(user);
            } else if (action == -2) {
                user.showPurchase();

            }
        }
    }

    public void actionOfProductMenu(User user, Product product) {
        while (true) {
            System.out.println("\n" + product.toString());

            int action = HelpForUser.tryToRead(0, 2, HelpForUser.stringActionOfProductMenu);
            switch (action) {
                case 0:
                    return;

                case 1:
                    user.byProduct(product);
                    return;

                case 2:
                    user.addToBasket(product);
                    return;
            }
        }
    }

    public void basketMenu(User user) {

        while (true) {
            if (user.basketSize() != 0) {
                user.showBasket();
                int action = HelpForUser.tryToRead(-2, user.basketSize(), HelpForUser.stringBasketMenu);
                if (action > 0) {
                    user.byFromBasket(action - 1);
                    System.out.println("\nУспешно куплено!\n");

                } else if (action == -1) {
                    user.byAllFromBasket();
                    System.out.println("\nУспешно куплено!\n");

                } else if (action == -2) {
                    int number=HelpForUser.tryToRead(1, user.basketSize(),HelpForUser.stringDeleteFromBasket)-1;
                    user.removeFromBasket(number);
                } else return;
            } else {
                System.out.println("\nНет товаров в корзине.\n");
                return;
            }
        }
    }

    public User login() {

        System.out.print("Введите логин --> ");

        Scanner in = new Scanner(System.in);
        String login = in.next();

        return AppData.findUserByLogin(login);
    }

    public boolean checkPassword(User user) {
        System.out.print("Введите пароль --> ");
        Scanner in = new Scanner(System.in);
        String password = in.next();
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            System.out.println("\nНеверный пароль!\n");
            return false;
        }
    }
}
