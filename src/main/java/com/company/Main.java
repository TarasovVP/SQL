package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DBTest dataBase = new DBTest();

        System.out.println("Введите имя: ");
        String first_name = sc.next();
        System.out.println("Введите фамилию: ");
        String last_name = sc.next();
        dataBase.addActor(first_name, last_name);

        System.out.println("Для получения адреса введите название города: ");
        String city = sc.next();
        dataBase.showAddress(dataBase.getIdCity(city));

    }
}
