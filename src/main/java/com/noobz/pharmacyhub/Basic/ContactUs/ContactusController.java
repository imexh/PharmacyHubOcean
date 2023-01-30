package com.noobz.pharmacyhub.Basic.ContactUs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactusController {
    @GetMapping("/contactus")
    public String viewContactUsPage(Model model)
    {
        return "BasicTemplates/contactus";
    }
}
