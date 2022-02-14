package company;

import java.util.ArrayList;
import java.util.Arrays;

public class AppData {
    private static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Category> categories=new ArrayList<>();

    public static User findUserByLogin(String login) {

        for (User user : AppData.getUsers()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        System.out.println("\nНеверный логин!\n");
        return null;
    }

    public static void initializeForStart() {
        ArrayList<Product> gameProducts = new ArrayList<>(Arrays.asList(
                new Product(1000, "Ведьмак", 0.98),
                new Product(2000, "Дота", 0.22),
                new Product(3000, "Герои Меча и Магии- 2", 1.0)));

        ArrayList<Product> bookProducts = new ArrayList<>(Arrays.asList(
                new Product(500, "Задача трех тел", 0.60),
                new Product(5000, "Дивный новый мир", 0.13),
                new Product(76, "Основание", 0.55)));

        ArrayList<Product> filmProducts = new ArrayList<>(Arrays.asList(
                new Product(5000000, "Звёздные войны", 0.88),
                new Product(60000, "Игра", 0.29),
                new Product(2300, "Начало", 0.63)));

        Category gameCategory = new Category("Игры", gameProducts);
        Category bookCategory = new Category("Книги", bookProducts);
        Category filmCategory = new Category("Фильмы", filmProducts);

        AppData.categories.add(gameCategory);
        AppData.categories.add(bookCategory);
        AppData.categories.add(filmCategory);

        AppData.getUsers().add(new User("Admin", "admin"));
    }


    public static ArrayList<User> getUsers() {
        return users;
    }
}
