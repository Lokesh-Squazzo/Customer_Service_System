//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.customerservice.models;

public class Complaint {
    private int customer_id;
    private Integer agent_id;
    private String category;
    private String description;
    private String status;

    public Complaint(int customer_id, Integer agent_id, String category, String description) {
        this.customer_id = customer_id;
        this.agent_id = agent_id;
        this.category = category;
        this.description = description;
        this.status = status;
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

    public void setStatus(String status) {
        this.status = status;
    }
}
