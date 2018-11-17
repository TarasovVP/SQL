package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBTest {

    public void addActor(String f_name, String l_name) {

        Properties props = getConnectionData();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String passwd = props.getProperty("db.passwd");

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
    public void showAddress(int city_id) {

        Properties props = getConnectionData();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String passwd = props.getProperty("db.passwd");

        String take = "select address, district, postal_code, phone from sakila.address where city_id = ?";

        try (Connection con = DriverManager.getConnection(url, user, passwd); PreparedStatement prpdSttmTake = con.prepareStatement(take)) {

            prpdSttmTake.setString(1, String.valueOf(city_id));

            ResultSet rs = prpdSttmTake.executeQuery();
            if(rs.next()){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

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