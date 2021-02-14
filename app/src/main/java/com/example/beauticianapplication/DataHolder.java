package com.example.beauticianapplication;

import java.util.ArrayList;

public class DataHolder {

    private static DataHolder single_instance = null;

    public static DataHolder getInstance()
    {
        if (single_instance == null)
            single_instance = new DataHolder();

        return single_instance;
    }


    int userID;
    String userFullName;
    String ppLink;

    //My Career Path
    int totalJobDone;
    int monthJobDone;
    int level;

    //My Financial Situation
    float totalEarnings;
    float monthlyEarnings;
    float annualEarnings;

    //My Past Appointments
    ArrayList<ArrayList<String>> pastAppointments;

    //My Incoming Appointments
    ArrayList<ArrayList<String>> incomingAppointments;

    //My Services
    ArrayList<ArrayList<String>> services;
    ArrayList<ArrayList<String>> allServices;

    //My Service Location
    ArrayList<ArrayList<String>> serviceLocation;

    //My Agenda
    ArrayList<ArrayList<String>> appointments;

    //My Inbox

    String phoneNumber;


    //My Service Hours
    ArrayList<ArrayList<String>> serviceSchedule;

    //City District
    ArrayList<City> cities;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getPpLink() {
        return ppLink;
    }

    public void setPpLink(String ppLink) {
        this.ppLink = ppLink;
    }

    public static void deleteDataHolder(){
        single_instance = null;
    }

    public int getTotalJobDone() {
        return totalJobDone;
    }

    public void setTotalJobDone(int totalJobDone) {
        this.totalJobDone = totalJobDone;
    }

    public int getMonthJobDone() {
        return monthJobDone;
    }

    public void setMonthJobDone(int monthJobDone) {
        this.monthJobDone = monthJobDone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(float totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public float getMonthlyEarnings() {
        return monthlyEarnings;
    }

    public void setMonthlyEarnings(float monthlyEarnings) {
        this.monthlyEarnings = monthlyEarnings;
    }

    public float getAnnualEarnings() {
        return annualEarnings;
    }

    public void setAnnualEarnings(float annualEarnings) {
        this.annualEarnings = annualEarnings;
    }

    public ArrayList<ArrayList<String>> getPastAppointments() {
        return pastAppointments;
    }

    public void setPastAppointments(ArrayList<ArrayList<String>> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }

    public ArrayList<ArrayList<String>> getIncomingAppointments() {
        return incomingAppointments;
    }

    public void setIncomingAppointments(ArrayList<ArrayList<String>> incomingAppointments) {
        this.incomingAppointments = incomingAppointments;
    }

    public ArrayList<ArrayList<String>> getServices() {
        return services;
    }

    public void setServices(ArrayList<ArrayList<String>> services) {
        this.services = services;
    }

    public ArrayList<ArrayList<String>> getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(ArrayList<ArrayList<String>> serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public ArrayList<ArrayList<String>> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<ArrayList<String>> appointments) {
        this.appointments = appointments;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<ArrayList<String>> getServiceSchedule() {
        return serviceSchedule;
    }

    public void setServiceSchedule(ArrayList<ArrayList<String>> serviceSchedule) {
        this.serviceSchedule = serviceSchedule;
    }

    public ArrayList<ArrayList<String>> getAllServices() {
        return allServices;
    }

    public void setAllServices(ArrayList<ArrayList<String>> allServices) {
        this.allServices = allServices;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
