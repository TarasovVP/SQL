package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя: ");
        String first_name = sc.next();
        System.out.println("Введите фамилию: ");
        String last_name = sc.next();

        DBTest dataBase = new DBTest();
        dataBase.addActor(first_name, last_name);

    }
}
