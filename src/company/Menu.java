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
            System.out.println("0. Выход\n");
            System.out.print("Выберите действие --> ");
            Scanner in = new Scanner(System.in);
            int action = in.nextInt();
            switch (action) {

                case 1:
                    User user = checkPassword(checkLogin());

                    if (user != null) {
                        categoryMenu(user);
                    }
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("\nНеверный ввод.\n");
                    break;
            }
        }
    }

    public void categoryMenu(User user) {
        while (true) {
            System.out.println("\nВоть Список категорий");
            for (int i = 0; i < AppData.categories.size(); i++) {
                System.out.println(i + 1 + ". " + AppData.categories.get(i).getName());
            }
            System.out.println("\n0. Назад");
            System.out.print("Выберите категорию --> ");

            Scanner in = new Scanner(System.in);


            if (in.hasNextInt()) {
                int number = in.nextInt();
                int action = number;
                if (number > 0 && number <= AppData.categories.size()) action = 1;
                switch (action) {
                    case 0:
                        return;

                    case 1:
                        Category category = AppData.categories.get(number - 1);
                        productsMenu(category, user);
                        break;
                    default:
                        System.out.println("\nНеверный номер категории\nВведите номер предложенной категории из списка\n");
                        break;
                }
            } else {
                System.out.println("\nНеверный формат, или номер категории.\nВводить нужно номер (число) выбранной категории\n");
            }
        }
    }

    public void productsMenu(Category category, User user) {

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

            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int number = in.nextInt();
                int action = number;
                if (number > 0 && number <= products.size()) action = 1;

                switch (action) {
                    case 1:
                        actionOfProductMenu(products.get(number - 1), user);
                        break;
                    case 0:
                        return;
                    case -1:
                        basketMenu(user);
                        break;

                    case -2:
                        if (user.lookingForProductsInPurchases())
                            user.showPurchase();
                        else System.out.println("Нет покупок, сходите в магазин!");
                        break;
                    default:
                        System.out.println("\nНеверное значение ввода.\n");
                        break;
                }
            }else{
                System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n Или номер товара\n");
            }
        }
    }

    public void actionOfProductMenu(Product product, User user) {
        boolean flag = false;
        while (!flag) {
            System.out.println("\n" + product.toString());
            System.out.println("\n1. Купить товар");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("0. Выход\n");
            System.out.print("Выберите действие --> ");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int action = in.nextInt();
                switch (action) {

                    case 0:
                        return;

                    case 1:
                        user.byProduct(product);
                        flag = true;
                        break;

                    case 2:
                        user.addToBasket(product);
                        flag = true;
                        break;

                    default:
                        System.out.println("\nНеверное значение действия\n");
                        break;
                }
            } else {
                System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n");
            }
        }
    }

    public void basketMenu(User user) {

        while (true) {
            if (user.lookingForProductsInBasket()) {
                System.out.println("\nНет товаров в корзине.\n");
                return;
            } else {
                user.showBasket();
                System.out.println("\nN. Купить товар под номером N");
                System.out.println("-1. Купить все товары в корзине");
                System.out.println("-2. Убрать товар из корзины");
                System.out.println("0. Выход");
                System.out.print("\nВведите значение --> ");
                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    int number = in.nextInt();
                    int action = number;
                    if (action > 0 && action <= user.takeCountsOfProduktsFromBasket()) action = 1;
                    switch (action) {
                        case 0:
                            return;

                        case 1:
                            user.byFromBasket(number - 1);
                            System.out.println("\nУспешно куплено!\n");
                            break;

                        case -1:
                            user.byAllFromBasket();
                            System.out.println("\nУспешно куплено!\n");
                            break;

                        case -2:
                            user.removeFromBasket(number - 1);
                            break;

                        default:
                            System.out.println("\nНеверное значение действия");
                            break;
                    }
                } else {
                    System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n Или номер товара\n");
                }
            }
        }
    }

    public User checkLogin() {
        while (true) {

            System.out.print("Введите логин --> ");

            Scanner in = new Scanner(System.in);
            String login = in.next();

            User user = AppData.findUserByLogin(login);

            if (user != null) {
                return user;
            }
        }
    }

    public User checkPassword(User user) {
        while (true) {
            System.out.print("Введите пароль --> ");
            Scanner in = new Scanner(System.in);
            String password = in.next();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("\nНеверный пароль!\n");
                return null;
            }
        }
    }
}
