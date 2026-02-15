package com.customerservice.repository;
import com.customerservice.dbconfig.DbConnect;
import com.customerservice.models.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ComplaintRepo {
    Connection connection = DbConnect.getConnection();

    public void fileComplaint(Complaint complaint){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO complaint (customer_id,agent_id,category,description) VALUES(?,?,?,?)"
            );
            preparedStatement.setInt(1,complaint.getCustomer_id());

            if(complaint.getAgent_id() != null){
                preparedStatement.setInt(2,complaint.getAgent_id());
            }else {
                preparedStatement.setNull(2, Types.INTEGER);
            }
            preparedStatement.setString(3,complaint.getCategory());
            preparedStatement.setString(4, complaint.getDescription());
            int i = preparedStatement.executeUpdate();
            if(i >0){
                System.out.println("Complaint is Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Complaint> getAllComplaints(){
        List<Complaint> complaints = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM complaint"
            );
            ResultSet resultSet = ps.executeQuery();
            resultSet.beforeFirst();

            while (resultSet.next()){
                complaints.add(new Complaint(
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("agent_id"),
                        resultSet.getString("category"),
                        resultSet.getString("description")
                ));
            }
            return complaints;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getStatus(String phone){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id from complaint where phone = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next())
                return resultSet.getString("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Give the Correct Phone Number";
    }
}
