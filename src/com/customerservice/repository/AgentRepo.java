package com.customerservice.repository;

import com.customerservice.dbconfig.DbConnect;
import com.customerservice.dto.AvailableAgents;
import com.customerservice.models.Agent;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AgentRepo {
    Connection connection = DbConnect.getConnection();

    public List<AvailableAgents> getAllAvailableAgents(){
        List<AvailableAgents> availableAgentsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(
                    "select id,skill from agent where is_available = true",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                availableAgentsList.add(new AvailableAgents(
                        resultSet.getInt("id"),
                        resultSet.getString("skill")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableAgentsList;
    }

    public String getAgentName(int id){
        try{
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT name from agent where id=?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            selectStatement.setInt(1,id);
            ResultSet resultSet=selectStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next()){
                return resultSet.getString("name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isAgentAvailable(String skill){
        try{
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * from agent where is_available = true AND skill = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            selectStatement.setString(1,skill);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Integer agentId(String category){
        try{
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * from agent where is_available = true AND skill = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            selectStatement.setString(1,category);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.first()) {
                int id = resultSet.getInt("id");
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE agent SET is_available=false where id = ?");
                updateStatement.setInt(1,id);
                updateStatement.executeUpdate();
                return id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void makeAgentFree(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE agent SET is_available =? where id=?"
            );
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,id);
            int i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    public List<Agent> getAllAgent(){
    //        return
    //    }
}
