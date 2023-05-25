package com.ussdwork.ussd.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userinfo")
public class User {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String pin;
    private double balance;
    private double airtimeBalance;

    // getters and setters
    public User() {
        super();
    }

    public User(String name, String phoneNumber, String pin, double balance, double airtimeBalance) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
        this.balance = balance;
        this.airtimeBalance = airtimeBalance;
    }

    public double getAirtimeBalance() {
        return airtimeBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAirtimeBalance(double airtimeBalance) {
        this.airtimeBalance = airtimeBalance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
