package com.customerservice.presentation;

import com.customerservice.services.CustomerService;

public class MenuRender {
    CustomerService customerService = new CustomerService();
    InputReader ir = new InputReader();

    public void mainMenu() {
        System.out.println("-------------Welcome To MAVDES Customer Service--------------");
        System.out.println("1. Add Agent");
        System.out.println("2. File Complaint");
        System.out.println("3. Check Complaint Status");
        System.out.println("4. Check All Category");
        System.out.println("5. Get All Complaints");
        System.out.println("6. Get All Customers");
        System.out.println("7. Exit");
        int choice = this.ir.getInt();
        switch (choice) {
            case 1:
                this.addAgentMenu();
                break;
            case 2:
                this.addComplaintMenu();
                break;
            case 3:
                this.checkComplaintMenu();
                break;
            case 4:
                System.out.println("\n\n\n");
                this.checkAllCategoryMenu();
                break;
            case 5:
                customerService.getAllComplaints();
                break;
            case 6:
                customerService.getAllCustomers();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Enter Valid Choice");
        }

    }

    private void addAgentMenu() {
        System.out.println("\n\n\n\n\n");
        System.out.print("Enter Agent's Name :- ");
        String name = this.ir.getString();
        System.out.print("Enter Agent's Phone Number :- ");
        String phone = this.ir.getString();
        System.out.println("Below Are The Skill");
        this.checkAllCategoryMenu();
        System.out.print("Enter Agent's Skill :- ");
        String skill = this.ir.getString();
        customerService.addAgent(name, phone, skill);
    }

    private void addComplaintMenu() {
        System.out.println("\n\n\n\n\n");
        System.out.println("----Customer Details First-----");
        System.out.print("Enter Name :- ");
        String name = this.ir.getString();
        System.out.print("Enter Phone Number :- ");
        String phone = this.ir.getString();
        customerService.addCustomer(name, phone);
        System.out.println("----Complaint Details-----");
        this.checkAllCategoryMenu();
        System.out.print("Enter Category :- ");
        String category = this.ir.getString();
        System.out.print("Enter Description of Complaint :- ");
        String description = this.ir.getString();
        customerService.filComplaint(phone,category, description);
    }

    private void checkComplaintMenu() {
        System.out.println("\n\n\n\n\n");
        System.out.print("Enter Phone Number: ");
        String phone = this.ir.getString();
    }

    private void checkAllCategoryMenu() {
        System.out.println("----------Complaint Category---------");
        System.out.println("AC");
        System.out.println("Fridge");
        System.out.println("Cooler");
        System.out.println("Oven");
        System.out.println("Washing Machine");
    }
}
