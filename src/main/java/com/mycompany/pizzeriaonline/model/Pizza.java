/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeriaonline.model;

/**
 *
 * @author lenovo
 */

import java.util.List;

public class Pizza {
    private final String name;
    private final double price;
    private final List<String> ingredients;

    public Pizza(String name, double price, List<String> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - " + price + " PLN (" + String.join(", ", ingredients) + ")";
    }
}
