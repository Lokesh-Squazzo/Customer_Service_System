package com.customerservice.repository;
import com.customerservice.dbconfig.DbConnect;
import com.customerservice.models.Complaint;
import com.customerservice.dto.ComplaintStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintRepo {
    Connection connection = DbConnect.getConnection();

    public List<Complaint> getAllComplaints(){
        List<Complaint> complaints = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM complaint",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet resultSet = ps.executeQuery();
            resultSet.beforeFirst();

            while (resultSet.next()){

                Complaint complaint =new Complaint(
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("agent_id"),
                        resultSet.getString("category"),
                        resultSet.getString("description")
                );
                complaint.setStatus(resultSet.getString("status"));
                complaint.setResolved_by(resultSet.getString("resolved_by"));

                complaints.add(complaint);
            }
            return complaints;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getAgentId(int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT agent_id from complaint where id = ?"
            );
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next())
                return resultSet.getInt("agent_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getStatus(int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT status from complaint where customer_id = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if(resultSet.next())
                return resultSet.getString("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Give the Correct Phone Number";
    }

    public List<ComplaintStatus> getPendingStatus(){
        List<ComplaintStatus> complaintStatusList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id,category,status from complaint where status = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1,"PENDING");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                ComplaintStatus complaintStatus = new ComplaintStatus(
                        resultSet.getInt("id"),
                        resultSet.getString("status")
                );
                complaintStatus.setCategory(resultSet.getString("category"));
                complaintStatusList.add(complaintStatus);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaintStatusList;
    }

    public List<ComplaintStatus> getInProgressStatus(){
        List<ComplaintStatus> complaintStatusList = new ArrayList<>();
        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id,status,agent_id,estimated_resolution_time from complaint where status = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1,"IN_PROGRESS");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                ComplaintStatus complaintStatus = new ComplaintStatus(
                        resultSet.getInt("id"),
                        resultSet.getString("status")
                );
                complaintStatus.setEstimated_resolution_time(resultSet.getTimestamp(
                        "estimated_resolution_time").toLocalDateTime()
                );
                complaintStatus.setAgentId(resultSet.getInt("agent_id"));
                complaintStatusList.add(complaintStatus);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaintStatusList;
    }

    public int updateInProgress(int id, LocalDateTime currentTime, String resolveBy){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE complaint " +
                            "SET status=?, resolved_at=?, resolved_by=? " +
                            "where id=? AND status='IN_PROGRESS'"
            );

            preparedStatement.setString(1,"RESOLVED");
            preparedStatement.setTimestamp(2, Timestamp.valueOf(currentTime));
            preparedStatement.setString(3,resolveBy);
            preparedStatement.setInt(4,id);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updatePending(int id, int agentId){
        try{
            LocalDateTime estimated_resolution_time= LocalDateTime.now().plusSeconds(11);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE complaint SET agent_id=?, status=?,estimated_resolution_time=? where id=? AND status='PENDING'"
            );
            preparedStatement.setInt(1,agentId);
            preparedStatement.setString(2,"IN_PROGRESS");
            preparedStatement.setTimestamp(3,Timestamp.valueOf(estimated_resolution_time));
            preparedStatement.setInt(4,id);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

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


}

//    public List<ComplaintStatus> getAllStatus(){
//        List<ComplaintStatus> complaintStatusList = new ArrayList<>();
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT id,status from complaint",
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY
//            );
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.beforeFirst();
//            while (resultSet.next()){
//                complaintStatusList.add(new ComplaintStatus(
//                        resultSet.getInt("id"),
//                        resultSet.getString("status")
//                ));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return complaintStatusList;
//    }
