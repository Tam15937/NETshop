package company;
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
        return "Название: "+name + "\t\tЦена: " + prise + "\t\tРейтинг: " + rate+"\t\t";
    }
}
