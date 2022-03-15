package entities;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable {


    private int priсe;
    private String name;
    private double rate;

    public Product(int price, String name, double rate) {
        this.name = name;
        this.priсe = price;
        this.rate = rate;
    }

    @Override
    public String toString() {
        String priseString = NumberFormat.getInstance().format(this.priсe);
        return String.format("  %1$-30s  %2$-10s  %3$-5.4s", this.name,priseString, this.rate);
    }

    public String toString(String category){
        NumberFormat priseLocale = NumberFormat.getInstance(Locale.getDefault());
        return String.format("  %1$-30s  %2$-10s  %3$-6s",this.name,category,this.priсe);
    }
    public int getPriсe() {
        return priсe;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
