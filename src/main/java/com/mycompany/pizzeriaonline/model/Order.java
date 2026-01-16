/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeriaonline.model;

/**
 *
 * @author lenovo
 */

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private final String customerName;
    private final List<Pizza> pizzas = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;

    public Order(String customerName) {
        this.id = IdGenerator.nextId();
        this.customerName = customerName;
    }

    public int getId() { return id; }
    public List<Pizza> getPizzas() { return pizzas; }
    public OrderStatus getStatus() { return status; }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public boolean canCancel() {
        return status == OrderStatus.CREATED || status == OrderStatus.READY;
    }

    public boolean setStatus(OrderStatus newStatus) {
        if (status == OrderStatus.DELIVERED) return false;
        this.status = newStatus;
        return true;
    }

    @Override
    public String toString() {
        return "Order{id=" + id +
                ", customer='" + customerName + '\'' +
                ", pizzas=" + pizzas.size() +
                ", status=" + status +
                '}';
    }
}
