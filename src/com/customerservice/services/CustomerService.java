package com.customerservice.services;
import com.customerservice.dto.ComplaintStatus;
import com.customerservice.models.Agent;
import com.customerservice.models.Complaint;
import com.customerservice.models.Customer;
import com.customerservice.repository.AgentRepo;
import com.customerservice.repository.ComplaintRepo;
import com.customerservice.repository.CustomerRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void refreshDataBase(){
        refreshInProgress();
        refreshPending();

    }

    public void refreshInProgress(){
        List<ComplaintStatus> complaintStatusList = complaintRepo.getInProgressStatus();
        LocalDateTime currentTime = LocalDateTime.now();
        if(! complaintStatusList.isEmpty()){
            for (ComplaintStatus complaintStatus : complaintStatusList){
                if(currentTime.isAfter(complaintStatus.getEstimated_resolution_time())){
                    int i = complaintRepo.updateInProgress(
                            complaintStatus.getId(),
                            currentTime,
                            agentRepo.getAgentName(
                                    complaintStatus.getAgentId()
                            )
                    );
                    if(i == 1){
                        agentRepo.makeAgentFree(complaintStatus.getAgentId());
                    }else{
                        System.out.println("Not updated");
                    }
                }
            }
        }else{
            System.out.println("No Record Found For In_Progress.....");
        }
    }

    public void refreshPending(){
        List<ComplaintStatus> complaintStatusList = complaintRepo.getPendingStatus();
        if(!complaintStatusList.isEmpty()){
            for (ComplaintStatus complaintStatus : complaintStatusList){
                if(agentRepo.isAgentAvailable(complaintStatus.getCategory())){
                    complaintRepo.updatePending(
                            complaintStatus.getId(),
                            agentRepo.agentId(complaintStatus.getCategory())
                    );
                }
            }
        }else {
            System.out.println("No Records");
        }
    }
    public int getId(String phone) {
        return customerRepo.getId(phone);
    }

    public Integer agentId(String category) {
        return agentRepo.agentId(category);
    }

    public String getStatus(String phone){
        return complaintRepo.getStatus(customerRepo.getId(phone));
    }
}
