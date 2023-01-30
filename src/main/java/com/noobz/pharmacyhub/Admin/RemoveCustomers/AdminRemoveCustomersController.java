package com.noobz.pharmacyhub.Admin.RemoveCustomers;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.CustomerAccount;
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
public class AdminRemoveCustomersController {
    @Autowired
    private AdminRemoveCustomersService adminRemoveCustomersService;

    @GetMapping("/removecustomers")
    public String viewAdminRemoveCustomers(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || !LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<CustomerAccount> customerAccountList = new ArrayList<>(adminRemoveCustomersService.getAllAccounts());
            model.addAttribute("accountsList", customerAccountList);
            return "AdminTemplates/adminremovecustomers";
        }
    }

    @PostMapping("/deletecustomer/{username}")
    public String deleteCustomerDetails(@PathVariable String username) throws ExecutionException, InterruptedException
    {
        adminRemoveCustomersService.deleteAccountDetails(username);
        return "redirect:/admin/removecustomers";
    }
}
