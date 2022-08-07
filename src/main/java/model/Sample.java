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
    private static ObservableList<Sample> products = FXCollections.observableArrayList();

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

    public static ObservableList<Sample> getProducts() {
        return products;
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

        if (products.size() == 0) {
            products.add(new Sample(4, "Throttle", 456.99, 1, 2, 6));
            products.add(new Sample(5, "Knots", 1.99, 20, 2, 50));
            products.add(new Sample(6, "Frame", 2.99, 4, 2, 12));
        }
    }

    public static ObservableList<Sample> getParts(){
        return parts;
    }





}
