package company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public Locale locale = Locale.GERMANY;

    public Menu() {
        Locale.setDefault(this.locale);
        AppData.initializeForStart();
    }

    public void authorizationMenu() {
        while (true) {
            int action = HelpForUser.tryToRead(0, 2, HelpForUser.stringMenu);
            User user;
            switch (action) {
                case 1:
                    user = login();
                    if (user != null)
                        if (checkPassword(user)) {
                            categoryMenu(user);
                        }
                    break;

                case 2:
                    user = registrationMenu();
                    if (user != null) categoryMenu(user);
                    break;
                case 0:
                    System.exit(0);
            }

        }
    }

    public User registrationMenu() {
        System.out.println("\nМеню регистрации\n" + "0. задайте логин/пароль этой цифрой, что бы вернуться\n");
        System.out.print("Придумайте логин --> ");
        Scanner in = new Scanner(System.in);
        String login = in.next();
        if (!login.equals("0")) {
            System.out.print("Придумайте пароль --> ");
            String password = in.next();
            if (!password.equals("0")) {
                return AppData.registrate(login, password);
            }
        }
        return null;
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
                actionOfProductMenu(user, category, products.get(action - 1));
            } else if (action == 0) {
                return;
            } else if (action == -1) {
                basketMenu(user, category);
            } else if (action == -2) {
                productReport(user);

            }
        }
    }

    public void actionOfProductMenu(User user, Category category, Product product) {
        while (true) {
            System.out.format(" |%1$-30s |%2$-10s |%3$-10s\n", "Название", "Цена", "Рейтинг");
            System.out.println(product.toString());

            int action = HelpForUser.tryToRead(0, 2, HelpForUser.stringActionOfProductMenu);
            switch (action) {
                case 0:
                    return;

                case 1:
                    user.byProduct(category, product);
                    productReport(user);
                    return;

                case 2:
                    user.addToBasket(category, product);
                    return;
            }
        }
    }

    public void basketMenu(User user, Category category) {

        while (true) {
            if (user.basketSize() != 0) {
                user.showBasket();
                int action = HelpForUser.tryToRead(-2, user.basketSize(), HelpForUser.stringBasketMenu);
                if (action > 0) {
                    user.byFromBasket(category, action - 1);
                    System.out.println("\nУспешно куплено!\n");
                    productReport(user);
                } else if (action == -1) {
                    user.byAllFromBasket();
                    System.out.println("\nУспешно куплено!\n");
                    productReport(user);
                } else if (action == -2) {
                    int number = HelpForUser.tryToRead(1, user.basketSize(), HelpForUser.stringDeleteFromBasket) - 1;
                    user.removeFromBasket(category, number);
                } else return;
            } else {
                System.out.println("-------------------------------------------------------------------------------------------------------");
                System.out.println("Нет товаров в корзине.");
                System.out.println("-------------------------------------------------------------------------------------------------------");
                return;
            }
        }
    }

    public void productReport(User user) {

        user.showPurchase();
        System.out.println("\nИтого\t" + user.purchasePrice());


        System.out.print("\nНажмите Enter, что бы продолжить... ");
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
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
