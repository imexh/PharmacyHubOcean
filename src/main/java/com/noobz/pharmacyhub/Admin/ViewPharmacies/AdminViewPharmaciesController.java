package com.noobz.pharmacyhub.Admin.ViewPharmacies;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Basic.Login.LoginForm;
import com.noobz.pharmacyhub.Classes.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin")
public class AdminViewPharmaciesController {

    @Autowired
    private AdminViewPharmaciesService adminViewPharmaciesService;

    @GetMapping("/viewpharmacies")
    public String viewAdminViewPharmacies(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {

            List<Account> accountsList = new ArrayList<>(adminViewPharmaciesService.getAllAccounts());

            model.addAttribute("accountsList", accountsList);

            return "AdminTemplates/adminviewpharmacies";
        }
    }

    @PostMapping ("/deletepharmacy/{registrationId}")
    public String deletePharmacyDetails(@PathVariable String registrationId) throws ExecutionException, InterruptedException
    {
        adminViewPharmaciesService.deleteAccountDetails(registrationId);
        return "redirect:/admin/viewpharmacies";
    }

}
