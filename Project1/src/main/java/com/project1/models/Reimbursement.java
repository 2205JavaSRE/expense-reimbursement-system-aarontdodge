package com.project1.models;

public class Reimbursement {

    private int id;
    private String datetime;
    private boolean approved;
    private boolean reviewed;
    private String category;
    private double amount;
    private String requesterUsername;


    public Reimbursement() {
    }

    public Reimbursement(int id, String datetime, boolean reviewed, String category, double amount, boolean approved, String requesterUsername) {
        this.id = id;
        this.datetime = datetime;
        this.reviewed = reviewed;
        this.category = category;
        this.amount = amount;
        this.approved = approved;
        this.requesterUsername = requesterUsername;
    }


    public Reimbursement(String category, double amount, String requesterUsername) {
        this.category = category;
        this.amount = amount;
        this.requesterUsername = requesterUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
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
                "datetime='" + datetime + '\'' +
                ", approved=" + approved +
                ", reviewed=" + reviewed +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", requesterUsername='" + requesterUsername + '\'' +
                '}';
    }
}
