package com.noobz.pharmacyhub.Pharmacy.Inventory;

import com.noobz.pharmacyhub.Admin.ViewAds.ViewAdsService;
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
@RequestMapping("/pharmacy")
public class PharmacyInventoryController {

    @Autowired
    private PharmacyInventoryService pharmacyInventoryService;

    @GetMapping("/viewinventory")
    public String viewPharmacyInventory(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<Medicine> medicineList = new ArrayList<>(pharmacyInventoryService.getAllMedicine(LoginController.registrationId));
            model.addAttribute("medicineList", medicineList);
            return "PharmacyTemplates/pharmacyviewinventory";
        }
    }

    @PostMapping("viewinventory/{medicineId}")
    public String viewMedicineDetails(@PathVariable String medicineId, Model model) throws ExecutionException, InterruptedException
    {
        Medicine medicine = pharmacyInventoryService.getMedicineDetails(medicineId);
        model.addAttribute("medicine", medicine);
        return "PharmacyTemplates/pharmacymedicineview";
    }

    @PostMapping("/deletemedicine/{medicineId}")
    public String deleteMedicineDetails(@PathVariable String medicineId) throws ExecutionException, InterruptedException
    {
        pharmacyInventoryService.deleteMedicine(medicineId);
        return "redirect:/pharmacy/viewinventory";
    }
}
