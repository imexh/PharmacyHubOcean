package com.noobz.pharmacyhub.Pharmacy.Prescriptions;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyViewPrescriptionsController {

    @Autowired
    private PharmacyViewPrescriptionsService pharmacyViewPrescriptionsService;

    @GetMapping("/viewprescriptions")
    public String viewPrescriptions(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<Prescription> prescriptionsList = new ArrayList<>(pharmacyViewPrescriptionsService.getAllPrescriptions(LoginController.registrationId));
            model.addAttribute("prescriptionsList", prescriptionsList);
            return "PharmacyTemplates/pharmacyviewprescriptions";
        }
    }

    @PostMapping("/viewprescriptions/{prescriptionId}")
    public String viewPrescriptionDetails(@PathVariable String prescriptionId, Model model) throws ExecutionException, InterruptedException
    {
        Prescription prescription = pharmacyViewPrescriptionsService.getPrescriptionDetails(prescriptionId);
        model.addAttribute("prescription", prescription);

        if (prescription.getStatus().equals("open"))
        {
            model.addAttribute("prescriptionForm", new PrescriptionForm());
            return "PharmacyTemplates/pharmacyviewopenprescription";
        }
        else
        {
            return "PharmacyTemplates/pharmacyviewacceptedprescription";
        }

    }

    @PostMapping("/deleteprescription/{prescriptionId}")
    public String deletePrescriptionDetails(@PathVariable String prescriptionId) throws ExecutionException, InterruptedException
    {
        pharmacyViewPrescriptionsService.deletePrescription(prescriptionId);
        return "redirect:/pharmacy/viewprescriptions";
    }

    @PostMapping("/acceptprescription/{prescriptionId}")
    public String acceptPrescription(@PathVariable String prescriptionId, Model model, @ModelAttribute PrescriptionForm prescriptionForm) throws ExecutionException, InterruptedException {
        model.addAttribute("prescriptionForm", prescriptionForm);
        Prescription prescription = new Prescription();

        prescription.setCustomerUsername(pharmacyViewPrescriptionsService.getPrescriptionDetails(prescriptionId).getCustomerUsername());
        prescription.setDate(pharmacyViewPrescriptionsService.getPrescriptionDetails(prescriptionId).getDate());
        prescription.setLink(pharmacyViewPrescriptionsService.getPrescriptionDetails(prescriptionId).getLink());
        prescription.setPharmacyId(LoginController.registrationId);
        prescription.setPrescriptionId(prescriptionId);
        prescription.setPrice(prescriptionForm.getPrice());
        prescription.setRemarks(prescriptionForm.getRemarks());
        prescription.setStatus("accepted");

        if (pharmacyViewPrescriptionsService.getPrescriptionDetails(prescriptionId).getStatus().equals("accepted"))
        {
            //Todo: Already accepted error
            return "redirect:/pharmacy/viewprescriptions";
        }
        else {
            pharmacyViewPrescriptionsService.updatePrescriptionDetails(prescription);
            return "redirect:/pharmacy/viewprescriptions";
        }
    }

}
