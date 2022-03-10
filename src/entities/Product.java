package entities;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable {


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
        return String.format("  %1$-30s  %2$-10s  %3$-5.4s", this.name,priseString, this.rate);
    }

    public String toString(String category){
        NumberFormat priseLocale = NumberFormat.getInstance(Locale.getDefault());
        return String.format("  %1$-30s  %2$-10s  %3$-6s",this.name,category,this.prise);
    }
    public int getPrise() {
        return prise;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
