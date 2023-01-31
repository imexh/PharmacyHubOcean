package com.noobz.pharmacyhub.Admin.AddPharmacies;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Other.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin")
public class AdminAddPharmaciesController {
    @Autowired
    private AdminAddPharmaciesService adminAddPharmaciesService;

    @GetMapping("/addpharmacy")
    public String viewAddPharmacyPage(Model model)
    {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("signupDetails", new AddPharmaciesForm());
            return "AdminTemplates/adminaddpharmacy";
        }
    }

    @PostMapping("/addpharmacy")
    public String getAddPharmacy(@ModelAttribute AddPharmaciesForm addPharmaciesForm, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("signupDetails", addPharmaciesForm);

        if(adminAddPharmaciesService.hasAccount(addPharmaciesForm.getRegistrationId()))
        {
            //Todo:account already exists error
            return "AdminTemplates/adminaddpharmacy";
        }
        else
        {
            Account account = new Account();
            PasswordSecurity security = new PasswordSecurity();
            account.setAll(addPharmaciesForm.getRegistrationId(), addPharmaciesForm.getPharmacyName(),
                    addPharmaciesForm.getContactNo(), addPharmaciesForm.getOwnerName(),
                    addPharmaciesForm.getProvince(), addPharmaciesForm.getDistrict(),
                    addPharmaciesForm.getCity(), security.encryptPassword(addPharmaciesForm.getPassword()), addPharmaciesForm.getImageUrl());

            adminAddPharmaciesService.saveAccountDetails(account);

            //Todo:account successfully created banner

            return "redirect:/admin/viewpharmacies";
        }
    }
}
