package company;

import java.util.Scanner;

public class HelpForUser {

    public static String stringMenu =
            ("\n\n1. Авторизоваться" +
                    "\n2. Зарегестрироваться" +
                    "\n0. Выход\n" +
                    "\nВыберите действие --> ");

    public static String stringProductsMenu =
            ("\n\nN. Выбрать товар под номером N" +
                    "\n-3. для сортировки" +
                    "\n-2. для просмотра покупок" +
                    "\n-1. для просмотра корзины" +
                    "\n0. для выхода\n" +
                    "\nВведите значение --> ");

    public static String stringCategoryMenu =
            ("\n\n0. Назад\n" +
                    "\nВыберите категорию --> ");

    public static String stringActionOfProductMenu =
            ("\n\n1. Купить товар" +
                    "\n2. Добавить товар в корзину" +
                    "\n0. Выход\n" +
                    "\nВыберите действие --> ");

    public static String stringBasketMenu =
            ("\n\nN. Купить товар под номером N" +
                    "\n-1. Купить все товары в корзине" +
                    "\n-2. Убрать товар из корзины" +
                    "\n0. Выход\n" +
                    "\nВведите значение --> ");
    public static String stringDeleteFromBasket = ("\nВведите номер удаляемого товара из корзины -->");

    public static String stringSortProductsMenu = ("\nЧто бы отсортировать, введите критерий сортировки,\n"
            +"'+' или '-' перед критерием,значит порядок сортировки по возрастанию и убыванию соответсвенно \n"
            + "\n1. Название"
            + "\n2. Цена"
            + "\n3. Рейтинг"
            + "\nВведите номер критерия --> ");


    public static int tryToRead(int min, int max, String strMenu) {
        while (true) {
            System.out.print(strMenu);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            int action;
            try {
                action = Integer.parseInt(input);
                if (action >= min && action <= max)
                    return action;
                else {
                    System.out.println("\nНеверный ввод\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n");
            }
        }
    }
}
