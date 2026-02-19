package com.customerservice.models;

public class Complaint {
    private int customer_id;
    private Integer agent_id;
    private String category;
    private String description;
    private String status,resolved_by;
    //Status: 'PENDING','IN_PROGRESS','RESOLVED'
    public Complaint(int customer_id, Integer agent_id, String category, String description) {
        this.customer_id = customer_id;
        this.agent_id = agent_id;
        this.category = category;
        this.description = description;
        this.status = status;
        this.resolved_by="Not Resolved Yet.";
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public Integer getAgent_id() {
        return this.agent_id;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.status;
    }
    public String getResolved_by() {
        return resolved_by;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResolved_by(String resolved_by) {
        this.resolved_by = resolved_by;
    }

    @Override
    public String toString() {
        return "Customer Id: "+customer_id+" "+
                "Agent Id: " +agent_id+" "+
                "Complaint Category: " + category+" "+
                ((description !=null)? description : "Description is Not Provided")+" "+
                "Status of Complaint: "+status+" " +
                "Resolved By: "+resolved_by;
    }
}
