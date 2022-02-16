package company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public Menu() {
        AppData.initializeForStart();
    }

    public void authorizationMenu() {
        while (true) {
            System.out.println("\n1. Авторизоваться");
            System.out.println("\n2. Зарегестрироваться");
            System.out.println("0. Выход\n");
            System.out.print("Выберите действие --> ");
            int action = HelpForUser.tryToRead(0, 2);
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
            ArrayList<Category> categories = AppData.categories;
            System.out.println("\nВоть Список категорий");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println(i + 1 + ". " + categories.get(i).getName());
            }
            System.out.println("\n0. Назад");
            System.out.print("Выберите категорию --> ");

            int action = HelpForUser.tryToRead(0, categories.size());

            if (action != 0) {
                Category category = categories.get(action - 1);
                productsMenu(user, category);
            } else return;

        }
    }

    public void productsMenu(User user, Category category) {

        ArrayList<Product> products = category.getProducts();

        while (true) {
            System.out.println("\nВоть Список товаров");

            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + ". " + products.get(i));
            }

            System.out.println("\nN. Выбрать товар под номером N");
            System.out.println("-2. для просмотра покупок ");
            System.out.println("-1. для просмотра корзины ");
            System.out.println("0. для выхода");
            System.out.print("\nВведите значение --> ");

            int action = HelpForUser.tryToRead(-2, products.size());
            if (action > 0) {
                actionOfProductMenu(user, products.get(action - 1));
            } else if (action == 0) {
                return;
            } else if (action == -1) {
                basketMenu(user);
            } else if (action == -2) {
                if (user.lookingForProductsInPurchases())
                    user.showPurchase();
                else System.out.println("Нет покупок, сходите в магазин!");
            }
        }
    }

    public void actionOfProductMenu(User user, Product product) {
        while (true) {
            System.out.println("\n" + product.toString());
            System.out.println("\n1. Купить товар");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("0. Выход\n");
            System.out.print("Выберите действие --> ");
            Scanner in = new Scanner(System.in);
            int action = HelpForUser.tryToRead(0, 2);
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
            user.showBasket();
            System.out.println("\nN. Купить товар под номером N");
            System.out.println("-1. Купить все товары в корзине");
            System.out.println("-2. Убрать товар из корзины");
            System.out.println("0. Выход");
            System.out.print("\nВведите значение --> ");

            int action = HelpForUser.tryToRead(-2, user.takeCountsOfProduktsFromBasket());
            if (action > 0) {
                user.byFromBasket(action - 1);
                System.out.println("\nУспешно куплено!\n");

            } else if (action == -1) {
                user.byAllFromBasket();
                System.out.println("\nУспешно куплено!\n");

            } else if (action == -2) {
                user.removeFromBasket(action - 1);
            } else return;
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
