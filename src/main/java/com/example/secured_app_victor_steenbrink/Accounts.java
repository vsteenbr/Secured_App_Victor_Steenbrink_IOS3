package com.example.secured_app_victor_steenbrink;

import androidx.annotation.NonNull;

public class Accounts {

    private int id;
    private String accountName;
    private double amount;
    private String iban;
    private String currency;

    public Accounts(int id, String accountName, double amount, String iban, String currency) {
        this.id = id;
        this.accountName = accountName;
        this.amount = amount;
        this.iban = iban;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", amount=" + amount +
                ", iban='" + iban + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

