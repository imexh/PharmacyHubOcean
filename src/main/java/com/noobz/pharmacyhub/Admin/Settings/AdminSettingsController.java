package com.noobz.pharmacyhub.Admin.Settings;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminSettingsController {
    @GetMapping("/settings")
    public String viewAdminSettings(Model model)
    {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            return "AdminTemplates/adminsettings";
        }
    }
}
