package com.noobz.pharmacyhub.Pharmacy.Settings;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Other.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/pharmacy")
public class PharmacySettingsController {

    @Autowired
    private PharmacySettingsService pharmacySettingsService;

    @GetMapping("/settings")
    public String viewPharmacyInventory(Model model)
    {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            model.addAttribute("accountForm", new Account());
            model.addAttribute("passwordForm", new ChangePasswordForm());
            model.addAttribute("registrationId", LoginController.registrationId);
            return "PharmacyTemplates/pharmacysettings";
        }
    }

    @PostMapping("/changename/{registrationId}")
    public String changePharmacyName(@PathVariable String registrationId, Model model, @ModelAttribute Account accountForm) throws ExecutionException, InterruptedException
    {
        model.addAttribute("accountForm", accountForm);
        Account account = new Account();
        Account account1 = pharmacySettingsService.getAccountDetails(registrationId);
        account.setAll(registrationId, accountForm.getPharmacyName(), account1.getContactNo(),
                account1.getOwnerName(), account1.getProvince(), account1.getDistrict(),
                account1.getCity(), account1.getPassword(), account1.getImageUrl());
        pharmacySettingsService.updateAccountDetails(account);
        return "redirect:/pharmacy/settings";
    }

    @PostMapping("/changecontact/{registrationId}")
    public String changeContact(@PathVariable String registrationId, Model model, @ModelAttribute Account accountForm) throws ExecutionException, InterruptedException
    {
        model.addAttribute("accountForm", accountForm);
        Account account = new Account();
        Account account1 = pharmacySettingsService.getAccountDetails(registrationId);
        account.setAll(registrationId, account1.getPharmacyName(), accountForm.getContactNo(),
                account1.getOwnerName(), account1.getProvince(), account1.getDistrict(),
                account1.getCity(), account1.getPassword(), account1.getImageUrl());
        pharmacySettingsService.updateAccountDetails(account);
        return "redirect:/pharmacy/settings";
    }

    @PostMapping("/changeownername/{registrationId}")
    public String changeOwnerName(@PathVariable String registrationId, Model model, @ModelAttribute Account accountForm) throws ExecutionException, InterruptedException
    {
        model.addAttribute("accountForm", accountForm);
        Account account = new Account();
        Account account1 = pharmacySettingsService.getAccountDetails(registrationId);
        account.setAll(registrationId, account1.getPharmacyName(), account1.getContactNo(),
                accountForm.getOwnerName(), account1.getProvince(), account1.getDistrict(),
                account1.getCity(), account1.getPassword(), account1.getImageUrl());
        pharmacySettingsService.updateAccountDetails(account);
        return "redirect:/pharmacy/settings";
    }

    @PostMapping("/changepassword/{registrationId}")
    public String changePassword(@PathVariable String registrationId, Model model, @ModelAttribute ChangePasswordForm passwordForm) throws ExecutionException, InterruptedException
    {
        model.addAttribute("passwordForm", passwordForm);
        Account account = new Account();
        Account account1 = pharmacySettingsService.getAccountDetails(registrationId);
        PasswordSecurity passwordSecurity = new PasswordSecurity();

        if (!passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword()))
        {
            //Todo: passwords do not match error
        }
        else if (!passwordForm.getCurrentPassword().equals(passwordSecurity.decryptPassword(account1.getPassword())))
        {
            //Todo: password is incorrect error
        }
        else
        {
            account.setAll(registrationId, account1.getPharmacyName(), account1.getContactNo(),
                    account1.getOwnerName(), account1.getProvince(), account1.getDistrict(),
                    account1.getCity(), passwordSecurity.encryptPassword(passwordForm.getNewPassword()), account1.getImageUrl());
            pharmacySettingsService.updateAccountDetails(account);
        }
        return "redirect:/pharmacy/settings";
    }
}
