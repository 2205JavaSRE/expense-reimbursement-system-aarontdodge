package com.project1.models;

public class Reimbursement {

    private int id;
    private boolean approved;
    private String category;
    private double amount;


    public Reimbursement() {
    }

    public Reimbursement(int id, String category, double amount, boolean approved) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", approved=" + approved +
                '}';
    }

}
