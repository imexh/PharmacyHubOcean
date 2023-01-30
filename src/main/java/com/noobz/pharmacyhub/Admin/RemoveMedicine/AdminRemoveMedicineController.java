package com.noobz.pharmacyhub.Admin.RemoveMedicine;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Medicine;
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
public class AdminRemoveMedicineController {

    @Autowired
    private AdminRemoveMedicineService adminRemoveMedicineService;

    @GetMapping("/removemedicine")
    public String viewAdminRemoveMedicine(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<Medicine> medicineList = new ArrayList<>(adminRemoveMedicineService.getAllMedicine());
            model.addAttribute("medicineList", medicineList);
            return "AdminTemplates/adminremovemedicine";
        }
    }

    @PostMapping("/deletemedicine/{medicineId}")
    public String deletePharmacyDetails(@PathVariable String medicineId) throws ExecutionException, InterruptedException
    {
        adminRemoveMedicineService.deleteMedicineDetails(medicineId);
        return "redirect:/admin/removemedicine";
    }
}
