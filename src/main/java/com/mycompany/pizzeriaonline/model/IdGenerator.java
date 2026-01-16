/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzeriaonline.model;

/**
 *
 * @author lenovo
 */

public class IdGenerator {
    private static int current = 1;

    public static int nextId() {
        return current++;
    }
}
