package com.noobz.pharmacyhub.Classes;

public class Advertisement {
    String companyName;
    String id;
    String email;
    String contact;
    String details;
    String companyAddress;
    String imageUrl;
    String topic;

    public Advertisement() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAll(String id, String topic, String companyName, String details, String contact, String email, String companyAddress, String imageUrl) {
        this.id = id;
        this.topic = topic;
        this.companyName = companyName;
        this.details = details;
        this.contact = contact;
        this.email = email;
        this.companyAddress = companyAddress;
        this.imageUrl = imageUrl;
    }
}
