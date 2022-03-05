package entities;

import helper.HelpForUser;

import java.util.ArrayList;
import java.util.Comparator;

public class Category {


    private String name;
    private ArrayList<Product> products;

    public Category(String name, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showProducts() {
        System.out.println("\nВоть Список товаров\n");
        System.out.format("\t %1$-30s  %2$-10s  %3$-10s\n\n", "Название", "Цена", "Рейтинг");
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(i + 1 + ". " + this.products.get(i));
        }
    }

    public void sortProductsMenu() {
        int action = HelpForUser.tryToRead(-3, 3, HelpForUser.stringSortProductsMenu);
        if(action==1)this.products.sort(Comparator.comparing(Product::getName));
        else if(action==-1)this.products.sort(Comparator.comparing(Product::getName).reversed());

        else if(action==2)this.products.sort(Comparator.comparingInt(Product::getPrise));
        else if(action==-2)this.products.sort(Comparator.comparingInt(Product::getPrise).reversed());

        else if(action==3)this.products.sort(Comparator.comparingDouble(Product::getRate));
        else if(action==-3)this.products.sort(Comparator.comparingDouble(Product::getRate).reversed());

    }

    /*public void sortProductsArray(int crit) {
        switch (crit) {
            case "": this.products.sort((p1, p2) -> p1.getName().compareTo(p2.getName())); break;
            case 2:
        }
    }*/

}
