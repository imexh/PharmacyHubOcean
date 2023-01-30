package com.noobz.pharmacyhub.Basic.Signup;

import com.noobz.pharmacyhub.Admin.ViewPharmacies.AdminViewPharmaciesService;
import com.noobz.pharmacyhub.Classes.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class SignupRestController {

    @Autowired
    private SignupService signupService;

//    @Autowired
//    private AdminViewPharmaciesService adminViewPharmaciesService;

    @PostMapping("/saveaccount")
    public String saveAccountDetails(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return signupService.saveAccountDetails(account);
    }

    @GetMapping("/saveaccount/{registrationId}")
    public Account getAccountDetails(@PathVariable String registrationId) throws ExecutionException, InterruptedException {
        return signupService.getAccountDetails(registrationId);
    }

    @PutMapping("/saveaccount")
    public String updateAccountDetails(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return signupService.updateAccountDetails(account);
    }

    @DeleteMapping("/saveaccount/{registrationId}")
    public String deleteAccountDetails(@PathVariable String registrationId) throws ExecutionException, InterruptedException {
        return signupService.deleteAccountDetails(registrationId);
    }

//    @GetMapping("/getaccounts")
//    public List<Account> getAllAccounts() throws ExecutionException, InterruptedException {
//        return adminViewPharmaciesService.getAllAccounts();
//    }
}
