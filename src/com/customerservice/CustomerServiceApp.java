package com.customerservice;

import com.customerservice.presentation.MenuRender;
import com.customerservice.services.CustomerService;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomerServiceApp {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        ScheduledExecutorService sEService = new ScheduledThreadPoolExecutor(1);
        sEService.scheduleAtFixedRate(()->{
            customerService.refreshDataBase();
        },0,20,TimeUnit.SECONDS);
        MenuRender menuRender = new MenuRender();
        while (true){
            menuRender.mainMenu();
        }

    }
}
