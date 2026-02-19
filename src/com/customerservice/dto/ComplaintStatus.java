package com.customerservice.dto;

import java.time.LocalDateTime;

public class ComplaintStatus {
    private final int id;
    private int agentId;
    private final String status;
    private String category,resolved_by;
    private LocalDateTime estimated_resolution_time;

    public ComplaintStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getResolved_by() {
        return resolved_by;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setResolved_by(String resolved_by) {
        this.resolved_by = resolved_by;
    }



    public LocalDateTime getEstimated_resolution_time() {
        return estimated_resolution_time;
    }

    public void setEstimated_resolution_time(LocalDateTime estimated_resolution_time) {
        this.estimated_resolution_time = estimated_resolution_time;
    }
}
