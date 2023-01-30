package com.noobz.pharmacyhub.Admin.ViewAds;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin")
public class AddAdsController {

    @Autowired
    private AddAdsService addAdsService;

    @GetMapping("/addadvertisement")
    public String viewAddAdvertisement(Model model)
    {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("addDetails", new Advertisement());
            return "AdminTemplates/adminaddadvertisement";
        }
    }

    @PostMapping("/addadvertisement")
    public String getAddAdvertise(@ModelAttribute Advertisement advertisement, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("addDetails", advertisement);

        if(addAdsService.hasAdvertisement(advertisement.getId()))
        {
            //Todo:account already exists error
            return "AdminTemplates/adminaddadvertisement";
        }
        else {
            Advertisement advertisement1 = new Advertisement();
            advertisement1.setAll(advertisement.getId(), advertisement.getTopic(),
                    advertisement.getCompanyName(), advertisement.getDetails(),
                    advertisement.getContact(), advertisement.getEmail(),
                    advertisement.getCompanyAddress(), advertisement.getImageUrl());

            addAdsService.saveAdvertisement(advertisement1);

            //Todo:account successfully created banner

            return "redirect:/admin/viewads";

        }

    }
}
