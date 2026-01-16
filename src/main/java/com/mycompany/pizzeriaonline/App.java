/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pizzeriaonline;

/**
 *
 * @author lenovo
 */

import com.mycompany.pizzeriaonline.model.Customer;
import com.mycompany.pizzeriaonline.model.Order;
import com.mycompany.pizzeriaonline.model.Pizza;
import com.mycompany.pizzeriaonline.service.Menu;
import com.mycompany.pizzeriaonline.service.OrderService;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Menu menu = new Menu();
        OrderService orderService = new OrderService(menu);
        Customer customer = new Customer(1, "Student");

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Pizzeria Online ===");
                System.out.println("1) Pokaz menu");
                System.out.println("2) Zloz zamowienie");
                System.out.println("3) Pokaz zamowienie po ID");
                System.out.println("4) Zmien status zamowienia");
                System.out.println("5) Anuluj zamowienie");
                System.out.println("6) Lista wszystkich zamowien");
                System.out.println("0) Wyjscie");
                System.out.print("Wybor: ");

                int choice = readInt(sc);

                if (choice == 0) {
                    System.out.println("Koniec programu.");
                    break;
                }

                switch (choice) {
                    case 1 -> showMenu(menu);

                    case 2 -> {
                        Order order = orderService.createOrder(customer.getName());

                        System.out.println("\nZamowienie ID: " + order.getId());
                        showMenu(menu);
                        System.out.println("Wybierz 1-3 pizze (0 = koniec):");

                        while (order.getPizzas().size() < 3) {
                            int x = readInt(sc);
                            if (x == 0) break;

                            Pizza pizza = menu.getPizzaByNumber(x);
                            if (pizza == null) {
                                System.out.println("Zly numer.");
                            } else {
                                order.addPizza(pizza);
                                System.out.println("Dodano: " + pizza.getName());
                            }
                        }

                        if (order.getPizzas().isEmpty()) {
                            System.out.println("Nie wybrano pizz. Zamowienie usuniete.");
                            orderService.removeOrder(order.getId());
                        } else {
                            System.out.println("Zamowienie utworzone.");
                            System.out.println(order);
                        }
                    }

                    case 3 -> {
                        Order o = askOrder(orderService, sc);
                        if (o != null) System.out.println(o);
                    }

                    case 4 -> {
                        Order o = askOrder(orderService, sc);
                        if (o == null) break;

                        System.out.println("Nowy status: 1=CREATED 2=READY 3=DELIVERED 4=CANCELLED");
                        int s = readInt(sc);
                        boolean ok = orderService.changeStatus(o.getId(), s);
                        System.out.println(ok ? "Status zmieniony." : "Nie udalo sie zmienic statusu.");
                    }

                    case 5 -> {
                        Order o = askOrder(orderService, sc);
                        if (o == null) break;

                        boolean ok = orderService.cancelOrder(o.getId());
                        System.out.println(ok ? "Zamowienie anulowane." : "Nie mozna anulowac.");
                    }

                    case 6 -> {
                        List<Order> all = orderService.getAllOrders();
                        if (all.isEmpty()) {
                            System.out.println("Brak zamowien.");
                        } else {
                            System.out.println("\nZAMOWIENIA:");
                            for (Order o : all) System.out.println(o);
                        }
                    }

                    default -> System.out.println("Nieznana opcja.");
                }
            }
        }
    }

    private static void showMenu(Menu menu) {
        System.out.println("\nMENU:");
        List<Pizza> pizzas = menu.getPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzas.get(i));
        }
    }

    private static Order askOrder(OrderService service, Scanner sc) {
        System.out.print("Podaj ID: ");
        int id = readInt(sc);
        Order o = service.getOrder(id);
        if (o == null) System.out.println("Brak zamowienia o takim ID.");
        return o;
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Podaj liczbe: ");
        }
        return sc.nextInt();
    }
}
