package com.noobz.pharmacyhub.Pharmacy.Dashboard;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyDashboardController {
    @Autowired
    private PharmacyDashboardService pharmacyDashboardService;

    @GetMapping("/dashboard")
    public String viewPharmacyDashboard(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("medicineCount", pharmacyDashboardService.countMedicine(LoginController.registrationId));
            model.addAttribute("customerCount", pharmacyDashboardService.countCustomers());
            return "PharmacyTemplates/pharmacydashboard";
        }
    }
}
