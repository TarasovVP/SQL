package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBTest {
    private Properties props = getConnectionData();

    private String url = props.getProperty("db.url");
    private String user = props.getProperty("db.user");
    private String passwd = props.getProperty("db.passwd");

     void addActor(String f_name, String l_name) {

        String insert = "insert into sakila.actor (first_name, last_name)" + "VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, passwd); PreparedStatement prpdSttmAdd = con.prepareStatement(insert)) {

            prpdSttmAdd.setString(1, f_name);
            prpdSttmAdd.setString(2, l_name);

            prpdSttmAdd.execute();

            System.out.println(f_name + " is added to first_name, " + l_name + " is added to last_name.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
     void showAddress(int city_id) {

        String take = "select address, district, postal_code, phone from sakila.address where city_id = ?";
        String address = "";
        String district = "";
        String postal_code = "";
        String phone = "";


        try (Connection con = DriverManager.getConnection(url, user, passwd); PreparedStatement prpdSttmTake = con.prepareStatement(take)) {

            prpdSttmTake.setString(1, String.valueOf(city_id));
            ResultSet rs = prpdSttmTake.executeQuery();

            if(rs.next()){
                address = rs.getString("address");
                district = rs.getString("district");
                postal_code = rs.getString("postal_code");
                phone = rs.getString("phone");
            }

            System.out.println("Для этого города адрес: " + address + ", район: " + district + ", почтовый индекс: " + postal_code + ", телефон: " + phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    int getIdCity(String str){
        int id = 0;
        String take = "select city_id from sakila.city where city = ?";
        try (Connection con = DriverManager.getConnection(url, user, passwd); PreparedStatement prpdSttmCity = con.prepareStatement(take)) {

            prpdSttmCity.setString(1, str);
            ResultSet rs = prpdSttmCity.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    private static Properties getConnectionData() {

        Properties props = new Properties();

        String fileName = "src/main/resources/db.properties";

        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return props;
    }
}