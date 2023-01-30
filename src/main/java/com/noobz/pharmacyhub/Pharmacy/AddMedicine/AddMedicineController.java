package com.noobz.pharmacyhub.Pharmacy.AddMedicine;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/pharmacy")
public class AddMedicineController {

    @Autowired
    private AddMedicineService addMedicineService;

    @GetMapping("/addmedicine")
    public String viewAddMedicinePage(Model model)
    {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("medicineDetails", new Medicine());
            return "PharmacyTemplates/pharmacyaddmedicine";
        }
    }

    @PostMapping("/addmedicine")
    public String getAddMedicine(@ModelAttribute Medicine medicine, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("medicineDetails", medicine);
        medicine.setPharmacyId(LoginController.registrationId);

        if(addMedicineService.hasMedicine(medicine.getMedicineId()))
        {
            //Todo:medicine already exists error
            return "redirect:/pharmacy/addmedicine";
        }
        else
        {
            addMedicineService.saveMedicineDetails(medicine);

            //Todo:medicine successfully added banner

            return "redirect:/pharmacy/viewinventory";
        }
    }
}
