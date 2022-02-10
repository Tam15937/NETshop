package company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static boolean userIsAuthorized = false;
    private static int numberOfAuthorizedUser;

    private static boolean categoryIsSelected = false;
    private static int numberOfSelectedCategory;

    private static boolean productIsSelected = false;
    private static int numberOfSelectedProduct;

    public Menu() {
        AppData.initializeForStart();
        this.menu();
    }

    public void menu() {
        while (true) {
            User user = login();
            numberOfAuthorizedUser = AppData.getUsers().indexOf(user);
            userIsAuthorized = checkPassword(user);

            while (!categoryIsSelected && userIsAuthorized) {
                categoryIsSelected = selectCategory();

                while (!productIsSelected && categoryIsSelected) {
                    numberOfSelectedProduct = selectProduct();
                    if (numberOfSelectedProduct != -1) {
                        while (productIsSelected) {
                            int action = actionOfProduct();
                            switch (action) {
                                case 0:
                                    productIsSelected = false;
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    user.addToBasket(AppData.categories.get(numberOfSelectedCategory).getProducts().get(numberOfSelectedProduct));
                                    productIsSelected = false;
                                    break;
                                default:
                                    System.out.println("\nНеверное значение действия\n");
                                    break;
                            }
                        }
                    } else if (categoryIsSelected) {
                        actionOfBasket();
                    }
                }
            }
        }
    }

    public User login() {
        boolean flag = false;
        User user = null;
        while (!flag) {

            System.out.print("\nВведите логин --> ");

            Scanner in = new Scanner(System.in);
            String login = in.next();

            user = AppData.findUserByLogin(login);
            if (user != null) {
                flag = true;
            } else {
                System.out.println("Неверный логин.\n");
            }
        }
        return user;
    }

    public boolean checkPassword(User user) {
        boolean flag = false;
        while (!flag) {
            System.out.print("\nQ. Выход \n");
            System.out.print("Введите пароль --> ");
            Scanner in = new Scanner(System.in);
            String password = in.next();
            if (user.getPassword().equals(password)) {
                flag = true;
            } else if (password.equals("Q") || password.equals("q")) {
                return false;
            } else {
                System.out.println("Неверный пароль.\n");
            }
        }
        return true;
    }

    public boolean selectCategory() {

        boolean flag = false;
        while (!flag) {
            System.out.println("\nВоть каталог / Список категорий товаров");
            for (int i = 0; i < AppData.categories.size(); i++) {
                System.out.println(i + 1 + ". " + AppData.categories.get(i).getName());
            }
            System.out.println("\nQ. Выход");
            System.out.print("Выберите категорию --> ");

            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int point = in.nextInt() - 1;
                if (point <= AppData.categories.size() && point >= 0) {
                    numberOfSelectedCategory = point;
                    flag = true;
                } else {
                    System.out.println("\nНеверный номер категории\nВведите номер предложенной категории из списка\n");
                }
            } else {
                String action = in.next();
                if (action.equals("Q") || action.equals("q")) {
                    userIsAuthorized = false;
                    return false;
                } else {
                    System.out.println("\nНеверный формат, или номер категории.\nВводить нужно номер (число) выбранной категории\n");
                }
            }
        }
        return true;
    }

    public int selectProduct() {

        ArrayList<Product> products = AppData.categories.get(numberOfSelectedCategory).getProducts();
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
                    productIsSelected = true;
                    flag = true;
                } else {
                    System.out.println("\nНеверный номер товара\nВведите номер предложенного товара из списка\n");
                }
            } else {
                String action = in.next();
                if (action.equals("B") || action.equals("b")) {
                    if (AppData.getUsers().get(numberOfAuthorizedUser).checkBasketForProduct())
                        return -1;
                    else System.out.println("\nНет товаров в корзине.\n");

                } else if (action.equals("Q") || action.equals("q")) {
                    categoryIsSelected = false;
                    return -1;
                } else {
                    System.out.println("\nНеверный формат номера товара.\nВводить нужно номер (число) выбранного товара\n");
                }
            }
        }
        return point;
    }

    public int actionOfProduct() {
        boolean flag = false;
        while (!flag) {
            System.out.println("\n1. Купить товар");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("Q. Выход\n");
            System.out.print("Выберите действие --> ");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                return in.nextInt();
            } else {
                String action = in.next();
                if (action.equals("Q") || action.equals("q")) {
                    return 0;
                } else {
                    System.out.println("\nНеверное значение действия\n");
                }
            }
        }
        return 0;
    }

    public void actionOfBasket() {
        User user = AppData.getUsers().get(numberOfAuthorizedUser);

        boolean flag = false;
        while (!flag) {
            if (!AppData.getUsers().get(numberOfAuthorizedUser).checkBasketForProduct()) {
                System.out.println("\nНет товаров в корзине.\n");
                flag = true;
            } else {
                user.showBasket();
                System.out.println("\nN. купить товар под номером N");
                System.out.println("Q. Выход");
                System.out.print("\nВведите значение --> ");
                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    int action = in.nextInt();
                    if (action != 0 && action<=user.takeCountsOfProduktsFromBasket()) {
                        user.removeFromBasket(action - 1);
                    } else {
                        System.out.println("\nНеверное значение действия");
                    }
                } else {
                    flag=true;
                }
            }
        }
    }
}
