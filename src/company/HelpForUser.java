package company;

import java.util.Scanner;

public class HelpForUser {
    public static int tryToRead(int min,int max){
        while(true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            int action;
            try {
                action = Integer.parseInt(input);
                if( action>=min && action<=max)
                return action;
                else{
                    System.out.println("\nНеверный ввод\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nНеверный формат.\nВводить нужно номер (число) выбранного действия\n");
            }
        }
    }
}
