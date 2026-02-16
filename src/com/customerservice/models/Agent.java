package com.customerservice.models;

public class Agent {
    private String name;
    private String skill;
    private String phone;
    private boolean is_available;

    public Agent(String name, String phone, String skill) {
        this.name = name;
        this.skill = skill;
        this.phone = phone;
        this.is_available = true;
    }

    public String getName() {
        return this.name;
    }

    public String getSkill() {
        return this.skill;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean isIs_available() {
        return this.is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}
