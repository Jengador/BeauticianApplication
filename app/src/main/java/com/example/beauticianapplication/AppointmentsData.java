package com.example.beauticianapplication;

public class AppointmentsData {
    String customer;
    String phone;
    String email;
    String startingTime;

    public AppointmentsData(String customer, String phone, String address, String startingTime) {
        this.customer = customer;
        this.phone = phone;
        this.email = address;
        this.startingTime = startingTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }
}
