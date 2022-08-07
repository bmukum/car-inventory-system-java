package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sample {
    private int id;
    private String name;

    private double price;
    private int stock;
    private int min;
    private int max;

    private static ObservableList<Sample> parts = FXCollections.observableArrayList();

    static {
        init();
    }

    public Sample(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public double getPrice() {return price;}

    public int getStock() {return stock;}

    public int getMin() {return min;}

    public int getMax() {return max;}

    public void setId() {this.id = id;}

    public void setName() {this.name = name;}

    public void setPrice() {this.price = price;}

    public void setStock() {this.stock = stock;}

    public void setMin() {this.min = min;}

    public void setMax() {this.max = max;}

    public static void init() {
        if (parts.size() == 0) {
            parts.add(new Sample(1, "Brakes", 4.99, 3, 2, 25));
            parts.add(new Sample(2, "Tires", 100.99, 4, 2, 90));
            parts.add(new Sample(3, "Wheel", 45.99, 0, 2, 5));
        }
    }

    public static ObservableList<Sample> getParts(){
        return parts;
    }





}
