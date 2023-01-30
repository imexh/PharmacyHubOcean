package com.noobz.pharmacyhub.Pharmacy.Pharmacy;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/pharmacy")
public class viewPharmacyController {
    @Autowired
    private viewPharmacyService viewpharmacyService;

    @GetMapping("/viewpharmacy")
    public String viewPharmacyInfo(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("pharmacy", viewpharmacyService.getPharmacyDetails(LoginController.registrationId));
            return "PharmacyTemplates/pharmacyviewpharmacy";
        }
    }
}
