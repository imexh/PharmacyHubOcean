package com.noobz.pharmacyhub.Admin.ViewAds;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Advertisement;
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
public class ViewAdsController {

    @Autowired
    private ViewAdsService viewAdsService;

    @GetMapping("/viewads")
    public String viewAdminViewAds(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<Advertisement> AdvertisementList = new ArrayList<>(viewAdsService.getAllAds());
            model.addAttribute("adsList", AdvertisementList);
            return "AdminTemplates/adminviewads";
        }
    }

    @PostMapping("/deletead/{id}")
    public String deletePharmacyDetails(@PathVariable String id) throws ExecutionException, InterruptedException
    {
        viewAdsService.deleteAdvertisementDetails(id);
        return "redirect:/admin/viewads";
    }
}
