package com.customerservice.services;
import com.customerservice.models.Agent;
import com.customerservice.models.Complaint;
import com.customerservice.models.Customer;
import com.customerservice.repository.AgentRepo;
import com.customerservice.repository.ComplaintRepo;
import com.customerservice.repository.CustomerRepo;
import java.util.List;

public class CustomerService {
    ComplaintRepo complaintRepo=new ComplaintRepo();
    CustomerRepo customerRepo = new CustomerRepo();
    AgentRepo agentRepo = new AgentRepo();

    public void addCustomer(String name, String phone) {
        customerRepo.addCustomer(new Customer(name,phone));
    }

    public void addAgent(String name, String phone, String skill) {
        agentRepo.addAgent(new Agent(name,phone,skill));
    }

    public void filComplaint(String phone, String category, String description) {
        complaintRepo.fileComplaint(new Complaint(customerRepo.getId(phone),agentRepo.agentId(category),category,description));

    }

    public void getAllComplaints(){
        List<Complaint> complaintList = complaintRepo.getAllComplaints();
        for(Complaint complaint : complaintList)
            System.out.println(complaint.toString());
    }

    public void getAllCustomers(){
        List<Customer> customers = customerRepo.getAllCustomer();
        for (Customer customer: customers)
            System.out.println(customer.toString());

    }
    public int getId(String phone) {
        return customerRepo.getId(phone);
    }

    public Integer agentId(String category) {
        return agentRepo.agentId(category);
    }
}
