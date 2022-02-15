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


                switch (action) {
                    case 1:
                        User user = login();
                        if (user != null)
                            if(checkPassword(user)) {
                            categoryMenu(user);
                        }
                        break;

                    case 2:
//                        User user
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("\nНеверный ввод.\n");
                        break;
                }

        }
    }

/*    public User registrationMenu() {
        Scanner in = new Scanner(System.in);
        String login = in.next();
        String password = in.next();
        return AppData.registration(login, password);
    }*/

    public void categoryMenu(User user) {
        while (true) {
            ArrayList<Category> categories = AppData.categories;
            System.out.println("\nВоть Список категорий");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println(i + 1 + ". " + categories.get(i).getName());
            }
            System.out.println("\n0. Назад");
            System.out.print("Выберите категорию --> ");

            Scanner in = new Scanner(System.in);

            if (in.hasNextInt()) {//todo try
                int number = in.nextInt();
                int action = number;

                if (action == 0) return;
                else if (action > 0 && action <= categories.size()) {
                    Category category = categories.get(number - 1);
                    productsMenu(user, category);
                } else {
                    System.out.println("\nНеверный номер категории\nВведите номер предложенной категории из списка\n");
                }

            }
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

            Scanner in = new Scanner(System.in);//todo
            if (in.hasNextInt()) {
                int number = in.nextInt();
                int action = number;
                if (number > 0 && number <= products.size()) action = 1; //todo

                switch (action) {
                    case 1:
                        actionOfProductMenu(products.get(number - 1), user);//todo
                        break;
                    case 0:
                        return;
                    case -1:
                        basketMenu(user);
                        break;

                    case -2:
                        if (user.lookingForProductsInPurchases())
                            user.showPurchase();//todo
                        else System.out.println("Нет покупок, сходите в магазин!");
                        break;
                    default:
                        System.out.println("\nНеверное значение ввода.\n");
                        break;
                }
            } else {
                System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n Или номер товара\n");
            }
        }
    }

    public void actionOfProductMenu(Product product, User user) {
        while (true) {
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
                        return;

                    case 2:
                        user.addToBasket(product);
                        return;

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
            if (user.lookingForProductsInBasket()) {//todo
                System.out.println("\nНет товаров в корзине.\n");
                return;
            } else {
                user.showBasket();
                System.out.println("\nN. Купить товар под номером N");
                System.out.println("-1. Купить все товары в корзине");
                System.out.println("-2. Убрать товар из корзины");
                System.out.println("0. Выход");
                System.out.print("\nВведите значение --> ");
                Scanner in = new Scanner(System.in);//todo
                if (in.hasNextInt()) {
                    int number = in.nextInt();
                    int action = number;
                    if (action > 0 && action <= user.takeCountsOfProduktsFromBasket()) action = 1;//todo
                    switch (action) {
                        case 0:
                            return;

                        case 1:
                            user.byFromBasket(number - 1);//todo
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
