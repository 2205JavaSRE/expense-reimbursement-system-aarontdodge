package com.project1.models;

public class Reimbursement {

    private int id;
    private boolean approved;
    private boolean reviewed;
    private String category;
    private double amount;
    private int requesterId;


    public Reimbursement() {
    }

    public Reimbursement(int id, boolean reviewed, String category, double amount, boolean approved, int requesterId) {
        this.id = id;
        this.reviewed = reviewed;
        this.category = category;
        this.amount = amount;
        this.approved = approved;
        this.requesterId = requesterId;
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

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", approved=" + approved +
                ", reviewed=" + reviewed +
                '}';
    }

}
