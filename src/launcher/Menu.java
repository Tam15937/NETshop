package launcher;

import utils.AppData;
import helper.HelpForUser;
import entities.Category;
import entities.Product;
import entities.User;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public abstract class Menu {
    public Locale locale = Locale.GERMANY;

    public static void categoryMenu(User user) {
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

    public static void productsMenu(User user, Category category) {


        while (true) {

            category.showProducts();
            ArrayList<Product> products = category.getProducts();

            int action = HelpForUser.tryToRead(-3, products.size(), HelpForUser.stringProductsMenu);
            if (action > 0) {
                actionOfProductMenu(user, category, products.get(action - 1));
            } else if (action == 0) {
                return;
            } else if (action == -1) {
                basketMenu(user, category);
            } else if (action == -2) {
                productReport(user);

            } else if (action == -3) {
                category.sortProductsMenu();
            }
        }
    }

    public static void actionOfProductMenu(User user, Category category, Product product) {
        while (true) {
            System.out.format("\n  %1$-30s  %2$-10s  %3$-10s\n", "Название", "Цена", "Рейтинг");
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

    public static void basketMenu(User user, Category category) {

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

    public static void productReport(User user) {

        user.showPurchase();
        System.out.println("\nИтого\t" + user.purchasePrice());


        System.out.print("\nНажмите Enter, что бы продолжить... ");
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
    }
}
