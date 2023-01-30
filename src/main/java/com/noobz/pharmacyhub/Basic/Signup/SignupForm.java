package com.noobz.pharmacyhub.Basic.Signup;

import com.noobz.pharmacyhub.Classes.Account;

public class SignupForm extends Account {
    String confirmPassword;

    public SignupForm() {}

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
