package company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static int numberOfAuthorizedUser;
    private static int numberOfSelectedCategory;
    private static int numberOfSelectedProduct;

    public Menu() {
        AppData.initializeForStart();
        this.menu();
    }

    public void menu() {
        numberOfAuthorizedUser = AppData.getUsers().indexOf(login());
        int n = categoryCheck();

    }

    public User login() {
        boolean flag = false;
        User user = null;
        while (!flag) {

            System.out.print("Введите логин --> ");

            Scanner in = new Scanner(System.in);
            String login = in.next();

            user = AppData.findUserByLogin(login);
            if (user != null) {
                flag = true;
            } else {
                System.out.println("Неверный логин.\n");
            }
        }
        return checkPassword(user);
    }

    public User checkPassword(User user) {
        boolean flag = false;
        while (!flag) {
            System.out.print("Введите пароль --> ");
            Scanner in = new Scanner(System.in);
            String password = in.next();
            if (user.getPassword().equals(password)) {
                flag = true;
            } else {
                System.out.println("Неверный пароль.\n");
            }
        }
        return user;
    }

    public int categoryCheck() {

        int point = -1;
        boolean flag = false;
        while (!flag) {
            System.out.println("\nВоть каталог / Список категорий товаров");
            for (int i = 0; i < AppData.categories.size(); i++) {
                System.out.println(i + 1 + ". " + AppData.categories.get(i).getName());
            }
            System.out.print("\nВыберите категорию --> ");

            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                point = in.nextInt() - 1;
                if (point <= AppData.categories.size() && point >= 0) {
                    numberOfSelectedCategory = point;
                    flag = true;
                } else {
                    System.out.println("\nНеверный номер категории\nВведите номер предложенной категории из списка\n");
                }
            } else {
                System.out.println("\nНеверный формат, или номер категории.\nВводить нужно номер (число) выбранной категории\n");
            }
        }
        return productsCheck(point);
    }

    public int productsCheck(int n) {

        ArrayList<Product> products = AppData.categories.get(n).getProducts();
        int point = -1;
        boolean flag = false;
        while (!flag) {
            System.out.println("\nВоть каталог / Список товаров");
            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + ". " + products.get(i));
            }
            System.out.println("\nN. Выбрать товар под номером N");
            System.out.println("Введите 'B' для просмотра корзины ");
            System.out.println("Введите 'Q' для выхода");
            System.out.print("\nВведите значение --> ");
            Scanner in = new Scanner(System.in);

            if (in.hasNextInt()) {
                point = in.nextInt() - 1;
                if (point <= products.size() && point >= 0) {
                    numberOfSelectedProduct = point;
                    flag = true;
                } else {
                    System.out.println("\nНеверный номер товара\nВведите номер предложенного товара из списка\n");
                }
            } else {
                String action = in.next();
                if (action.equals("B")|| action.equals("b")) {
                    if (AppData.getUsers().get(numberOfAuthorizedUser).checkBasketForProduct())
                        return actionOfBasket();
                    else System.out.println("\nНет товаров в корзине.\n");

                } else if (action.equals("Q")|| action.equals("q")) {
                    return categoryCheck();
                } else {
                    System.out.println("\nНеверный формат номера товара.\nВводить нужно номер (число) выбранного товара\n");
                }
            }
        }
        return actionOfProduct(products.get(point));
    }

    public int actionOfProduct(Product product) {
        System.out.println("\n1. Купить товар");
        System.out.println("2. Добавить товар в корзину");
        System.out.println("0. Выход\n");
        System.out.print("Выберите действие --> ");
        User user = AppData.getUsers().get(numberOfAuthorizedUser);
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int action = in.nextInt();
            switch (action) {
                case 0:
                    return productsCheck(numberOfSelectedCategory);

                case 1:
                    user.removeFromBasket(numberOfSelectedProduct);
                    break;

                case 2:
                    user.addToBasket(product);
                    break;

                default:
                    System.out.println("\nНеверное значение действия\n");
                    return actionOfProduct(product);
            }
        } else {
            System.out.println("\nНеверное значение действия\n");
            return actionOfProduct(product);
        }
        return productsCheck(numberOfSelectedCategory);
    }

    public int actionOfBasket() {
        User user = AppData.getUsers().get(numberOfAuthorizedUser);

        boolean flag = false;
        while (!flag) {
            if (!AppData.getUsers().get(numberOfAuthorizedUser).checkBasketForProduct()) {
                System.out.println("\nНет товаров в корзине.\n");
                return productsCheck(numberOfSelectedCategory);
            } else {
                user.showBasket();
                System.out.println("\nN. купить товар под номером N");
                System.out.println("0. Выход");
                System.out.print("\nВведите значение --> ");
                Scanner in = new Scanner(System.in);
                int action = in.nextInt();
                if (action != 0) {
                    user.removeFromBasket(action - 1);


                } else {
                    flag = true;
                }
            }
        }
        return productsCheck(numberOfSelectedCategory);
    }
}
