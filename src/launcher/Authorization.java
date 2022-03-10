package launcher;

import utils.AppData;
import helper.HelpForUser;
import entities.User;

import java.util.Scanner;

public class Authorization {

    public Authorization() {
    }

    public void authorizationMenu() {
        while (true) {
            AppData.todayDate();
            AppData.displayAllLoginsAndPasswords();
            int action = HelpForUser.tryToRead(0, 2, HelpForUser.stringMenu);
            User user;
            switch (action) {
                case 1:

                    user = login();
                    if (user != null)
                        if (checkPassword(user)) {
                            Menu.categoryMenu(user);
                        }
                    break;

                case 2:
                    user = registrationMenu();
                    if (user != null) {
                        Menu.categoryMenu(user);
                    }
                    break;
                case 0:
                    System.exit(0);
            }

        }
    }

    public User login() {
        System.out.println("\nМеню Входа\n" + "0. задайте логин/пароль этой цифрой, что бы вернуться");
        while (true) {
            String login = HelpForUser.returnInputText("Введите", "логин");

            if (login.equals("0")) return null;

            else if (AppData.findUserByLogin(login) != null) return AppData.findUserByLogin(login);
        }
    }

    public boolean checkPassword(User user) {
        while (true) {
            String password = HelpForUser.returnInputText("Введите", "пароль");
            if (password.equals("0")) return false;
            else if (user.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println("\nНеверный пароль!\n");
            }
        }
    }

    public User registrationMenu() {
        System.out.println("\nМеню регистрации\n" + "0. задайте логин/пароль этой цифрой, что бы вернуться\n");

        String login = createLogin();
        if (!login.equals("0")) {
            String password = createPassword();
            if (!password.equals("0")) {
                return AppData.registrate(login, password);
            } else return null;
        } else return null;
    }

    public String createLogin() {
        String login = HelpForUser.returnInputText("Придумайте", "логин");
        if (checkLogin(login)) {
            System.out.println("\nЛогин уже занят, придумайте другой\n");
            return createLogin();
        }
        return login;
    }

    public String createPassword() {
        String password = HelpForUser.returnInputText("Придумайте", "пароль");
        String confirmPassword = HelpForUser.returnInputText("Подтвердите", "пароль");
        if (!confirmPassword.equals(password)) {
            System.out.println("\nПароли не свопадают!\n");
            return createPassword();
        } else return password;
    }


    public boolean checkLogin(String login) {
        for (User user : AppData.getUsers()) {
            if (user.getLogin().equals(login)) return true;
        }
        return false;
    }

}
