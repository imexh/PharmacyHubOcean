package com.noobz.pharmacyhub.Admin.Dashboard;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard")
    public String viewPharmacyDashboard(Model model) throws ExecutionException, InterruptedException {

        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("pharmacyCount", adminDashboardService.countPharmacies());
            model.addAttribute("customerCount", adminDashboardService.countCustomers());
            model.addAttribute("medicineCount", adminDashboardService.countMedicine());
            model.addAttribute("adCount", adminDashboardService.countAdvertisements());

            return "AdminTemplates/admindashboard";
        }
    }
}
