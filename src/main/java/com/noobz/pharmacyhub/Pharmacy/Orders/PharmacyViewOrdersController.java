package com.noobz.pharmacyhub.Pharmacy.Orders;

import com.noobz.pharmacyhub.Basic.Login.LoginController;
import com.noobz.pharmacyhub.Classes.Medicine;
import com.noobz.pharmacyhub.Classes.Order;
import com.noobz.pharmacyhub.Pharmacy.Inventory.PharmacyInventoryService;
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
@RequestMapping("/pharmacy")
public class PharmacyViewOrdersController {
    @Autowired
    private PharmacyViewOrdersService pharmacyViewOrdersService;

    @GetMapping("/vieworders")
    public String viewPharmacyInventory(Model model) throws ExecutionException, InterruptedException {
        if(LoginController.registrationId == null || LoginController.registrationId.equals("admin"))
        {
            return "redirect:/login";
        }
        else {
            List<Order> orderList = new ArrayList<>(pharmacyViewOrdersService.getAllOrders(LoginController.registrationId));
            model.addAttribute("orderList", orderList);
            return "PharmacyTemplates/pharmacyvieworders";
        }
    }

    @PostMapping("/vieworders/{orderId}")
    public String deleteOrderDetails(@PathVariable String orderId) throws ExecutionException, InterruptedException
    {
        pharmacyViewOrdersService.deleteOrderDetails(orderId);
        return "redirect:/pharmacy/vieworders";
    }
}
