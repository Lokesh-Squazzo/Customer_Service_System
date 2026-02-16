
package com.customerservice.models;

public class Customer {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    @Override
    public String toString() {
        return "Customer Name: " +name+"   |   "+
                "Customer Phone Number: "+ phone;
    }
}
