package company;

import java.text.NumberFormat;

public class Product {


    private int prise;
    private String name;
    private double rate;

    public Product(int prise, String name, double rate) {
        this.name = name;
        this.prise = prise;
        this.rate = rate;
    }

    @Override
    public String toString() {
        String priseString = NumberFormat.getInstance().format(this.prise);
        return "Название: "+name + "\t\tЦена: " + priseString + "\t\tРейтинг: " + rate+"\t\t";
    }

    public String toString(Category category){
        String priseString = NumberFormat.getInstance().format(this.prise);
        return "Название: "+name  + "\t\tКатегория: " + category.getName()+"\t\t"+ "\t\tЦена: " + priseString;
    }
    public int getPrise() {
        return prise;
    }
}
