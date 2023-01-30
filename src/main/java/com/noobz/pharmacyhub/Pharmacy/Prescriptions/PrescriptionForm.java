package com.noobz.pharmacyhub.Pharmacy.Prescriptions;

public class PrescriptionForm {
    String price;
    String remarks;
    String prescriptionId;

    public PrescriptionForm() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
