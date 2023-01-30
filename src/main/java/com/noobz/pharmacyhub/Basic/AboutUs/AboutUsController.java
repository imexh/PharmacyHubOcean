package com.noobz.pharmacyhub.Basic.AboutUs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
    @GetMapping("/aboutus")
    public String viewAboutUsPage(Model model)
    {
        return "BasicTemplates/aboutus";
    }
}
