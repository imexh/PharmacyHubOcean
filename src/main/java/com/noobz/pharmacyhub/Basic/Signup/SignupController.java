package com.noobz.pharmacyhub.Basic.Signup;

import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Other.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String viewSignupPage(Model model)
    {
        model.addAttribute("signupDetails", new SignupForm());
        return "BasicTemplates/signup";
    }

    @PostMapping("/signup")
    public String getSignupPage(@ModelAttribute SignupForm signupForm, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("signupDetails", signupForm);

        if(!signupForm.getPassword().equals(signupForm.getConfirmPassword()))
        {
            //Todo:passwords do no match error
            return "BasicTemplates/signup";
        }
        else if(signupService.hasAccount(signupForm.getRegistrationId()))
        {
            //Todo:account already exists error
            return "BasicTemplates/signup";
        }
        else
        {
            Account account = new Account();
            PasswordSecurity security = new PasswordSecurity();
            account.setAll(signupForm.getRegistrationId(), signupForm.getPharmacyName(),
                    signupForm.getContactNo(), signupForm.getOwnerName(),
                    signupForm.getProvince(), signupForm.getDistrict(),
                    signupForm.getCity(), security.encryptPassword(signupForm.getPassword()), signupForm.getImageUrl());

            signupService.saveAccountDetails(account);

            //Todo:account successfully created banner

            return "redirect:/login";

        }


    }
}
