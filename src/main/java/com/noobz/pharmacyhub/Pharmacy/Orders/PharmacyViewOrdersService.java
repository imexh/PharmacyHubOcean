package com.noobz.pharmacyhub.Pharmacy.Orders;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Medicine;
import com.noobz.pharmacyhub.Classes.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PharmacyViewOrdersService {
    private static final String COLLECTION_NAME = "ordersFromCustomers";

    //service to get all
    public List<Order> getAllOrders(@RequestParam String pharmacyId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Order> orderList = new ArrayList<>();
        Order order = null;

        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("pharmacyId", pharmacyId);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            order = document.toObject(Order.class);
            orderList.add(order);
        }
        return orderList;
    }

    //service to delete an order object in the database
    public boolean deleteOrderDetails(@RequestParam String orderId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(orderId).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
