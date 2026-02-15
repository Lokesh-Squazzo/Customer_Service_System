package com.customerservice.services;

import com.customerservice.dbconfig.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerService {
    static Connection connection = DbConnect.getConnection();

    public static void addCustomer(String name, String phone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (name,phone) VALUES(?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            int i = preparedStatement.executeUpdate();
            if (i <= 0) {
                System.out.println("Customer Not Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addAgent(String name, String phone, String skill) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO agent (name,phone,skill) VALUES(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, skill);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Agent is Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void filComplaint(int cust_id, Integer agent_id, String category, String description) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO complaint (customer_id,agent_id,category,description) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, cust_id);
            if (agent_id != null) {
                preparedStatement.setInt(2, agent_id);
            } else {
                preparedStatement.setNull(2, 4);
            }

            preparedStatement.setString(3, category);
            preparedStatement.setString(4, description);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Complaint is Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getId(String phone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from customer where phone = ?", 1005, 1007);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Integer agentId(String category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from agent where is_available = true AND skill = ?", 1005, 1007);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
