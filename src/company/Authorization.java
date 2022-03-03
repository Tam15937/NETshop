package company;

import java.util.ArrayList;
import java.util.Scanner;

public class Authorization {

    public Authorization(){
        AppData.initializeForStart();
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
                    if (user != null) Menu.categoryMenu(user);
                    break;
                case 0:
                    System.exit(0);
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

    public User registrationMenu() {
        System.out.println("\nМеню регистрации\n" + "0. задайте логин/пароль этой цифрой, что бы вернуться\n");
        System.out.print("Придумайте логин --> ");
        Scanner in = new Scanner(System.in);
        String login = in.next();
        if (!login.equals("0") ) {
            while (true) {
                System.out.print("Придумайте пароль --> ");
                String password = in.next();
                if (!password.equals("0")) {
                    System.out.print("Подтвердите пароль --> ");
                    String confirmPassword = in.next();
                    if (confirmPassword.equals(password)) {
                        return AppData.registrate(login, password);
                    } else {
                        System.out.println("\nПароли не свопадают!\n");
                    }
                } else return null;
            }
        }
        return null;
    }

    public boolean checkLogin(String login){
        for(User user: AppData.getUsers()){
            if(user.getLogin().equals(login)) return true;
        }
        return false;
    }

}
