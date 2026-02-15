package com.customerservice.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.customerservice.dbconfig.DbConnect;
import com.customerservice.models.Customer;

public class CustomerRepo {
    Connection connection = DbConnect.getConnection();

    public void addCustomer(Customer customer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO customer (name,phone) VALUES(?,?)"
            );
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            int i = preparedStatement.executeUpdate();
            if(i <=0){
                System.out.println("Customer Not Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = null;
        try {
            customerList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("phone")
                ));
            }
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public int getId(String phone){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id from customer where phone = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next())
                return resultSet.getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
