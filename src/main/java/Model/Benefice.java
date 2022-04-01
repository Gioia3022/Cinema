package Model;

import java.util.ArrayList;

public class Benefice {
    private String name;
    private float discount;
    private ArrayList <String> names;
    private ArrayList <Float> discounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<Float> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<Float> discounts) {
        this.discounts = discounts;
    }
}
