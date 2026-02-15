package com.customerservice.repository;

import com.customerservice.dbconfig.DbConnect;
import com.customerservice.models.Agent;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AgentRepo {
    Connection connection = DbConnect.getConnection();
    public void addAgent(Agent agent){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO agent (name,phone,skill) VALUES(?,?,?)"
            );
            preparedStatement.setString(1,agent.getName());
            preparedStatement.setString(2, agent.getPhone());
            preparedStatement.setString(3, agent.getSkill());
            int i = preparedStatement.executeUpdate();
            if(i >0){
                System.out.println("Agent is Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Agent> getAllAgent(){
//        return
//    }

    public Integer agentId(String category){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * from agent where is_available = true AND skill = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1,category);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
