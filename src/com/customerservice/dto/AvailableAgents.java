package com.customerservice.dto;

public class AvailableAgents {
    private final int id;
    private final String skill;

    public AvailableAgents(int id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }
}
