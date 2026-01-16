/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeriaonline.service;

/**
 *
 * @author lenovo
 */

import com.mycompany.pizzeriaonline.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Pizza> pizzas = new ArrayList<>();

    public Menu() {
        pizzas.add(new Pizza("Margherita", 24.99, List.of("ser", "sos pomidorowy")));
        pizzas.add(new Pizza("Pepperoni", 29.99, List.of("ser", "pepperoni")));
        pizzas.add(new Pizza("Hawajska", 27.99, List.of("ser", "szynka", "ananas")));
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Pizza getPizzaByNumber(int number) {
        int idx = number - 1;
        if (idx < 0 || idx >= pizzas.size()) return null;
        return pizzas.get(idx);
    }
}
