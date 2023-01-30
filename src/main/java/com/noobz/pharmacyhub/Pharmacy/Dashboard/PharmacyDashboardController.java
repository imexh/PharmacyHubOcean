package com.noobz.pharmacyhub.Pharmacy.Dashboard;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyDashboardController {

    @GetMapping("/dashboard")
    public String viewPharmacyDashboard(Model model)
    {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            return "PharmacyTemplates/pharmacydashboard";
        }
    }
}
