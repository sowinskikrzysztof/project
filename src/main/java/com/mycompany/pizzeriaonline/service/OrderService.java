/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeriaonline.service;

/**
 *
 * @author lenovo
 */

import com.mycompany.pizzeriaonline.model.Order;
import com.mycompany.pizzeriaonline.model.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final Menu menu;
    private final Map<Integer, Order> orders = new HashMap<>();

    private static final double DELIVERY_FEE = 8.0;

    public OrderService(Menu menu) {
        this.menu = menu;
    }

    public Order createOrder(String customerName) {
        Order o = new Order(customerName);
        orders.put(o.getId(), o);
        return o;
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

    public boolean cancelOrder(int id) {
        Order o = orders.get(id);
        if (o == null) return false;
        if (!o.canCancel()) return false;
        return o.setStatus(OrderStatus.CANCELLED);
    }

    public boolean changeStatus(int id, int s) {
        Order o = orders.get(id);
        if (o == null) return false;

        OrderStatus status = switch (s) {
            case 1 -> OrderStatus.CREATED;
            case 2 -> OrderStatus.READY;
            case 3 -> OrderStatus.DELIVERED;
            case 4 -> OrderStatus.CANCELLED;
            default -> null;
        };

        if (status == null) return false;
        return o.setStatus(status);
    }

    public boolean setDeliveryAddress(int orderId, String address) {
        Order o = orders.get(orderId);
        if (o == null) return false;

        if (address == null) return false;
        String a = address.trim();
        if (a.length() < 5) return false;

        o.setDeliveryAddress(a);
        return true;
    }

    public double getDeliveryFee() {
        return DELIVERY_FEE;
    }
}
