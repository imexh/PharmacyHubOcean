package com.noobz.pharmacyhub.Basic.Login;

import com.noobz.pharmacyhub.Other.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class LoginController {

    public static String registrationId = null;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "abcd";

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String viewLoginPage(Model model)
    {
        model.addAttribute("loginDetails", new LoginForm());
        return "BasicTemplates/login";
    }

    @PostMapping("/login")
    public String getLoginPageData(@ModelAttribute LoginForm loginForm, Model model) throws ExecutionException, InterruptedException {

        model.addAttribute("loginDetails", loginForm);
        if(loginForm.getRegistrationId().equals(ADMIN_USERNAME) && loginForm.getPassword().equals(ADMIN_PASSWORD))
        {
            registrationId = "admin";
            return "redirect:/admin/dashboard";
        }
        else
        {
            PasswordSecurity security = new PasswordSecurity();

            if(loginService.hasAccount(loginForm.getRegistrationId()))
            {
                if(!loginForm.getPassword().equals(security.decryptPassword(loginService.getAccountDetails(loginForm.getRegistrationId()).getPassword())))
                {
                    //Todo: Wrong password error to html page

                    return "BasicTemplates/login";
                }
                else
                {
                    registrationId = loginForm.getRegistrationId();
                    return "redirect:/pharmacy/dashboard";
                }
            }
            else
            {
                //Todo: Add no account found error to html page

                return "BasicTemplates/login";
            }
        }


    }
}
