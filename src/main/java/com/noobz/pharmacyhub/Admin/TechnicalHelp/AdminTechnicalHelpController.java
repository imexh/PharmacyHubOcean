package com.noobz.pharmacyhub.Admin.TechnicalHelp;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminTechnicalHelpController {
    @GetMapping("/technicalhelp")
    public String viewAdminTechnicalHelp(Model model)
    {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            return "AdminTemplates/admintechnicalhelp";
        }
    }
}
