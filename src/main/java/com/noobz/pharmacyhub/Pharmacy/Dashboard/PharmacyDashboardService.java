package com.noobz.pharmacyhub.Pharmacy.Dashboard;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.CustomerAccount;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PharmacyDashboardService {

    private static final String COLLECTION_MEDICINE = "medicineDetails";
    private static final String COLLECTION_CUSTOMERS = "customerAccounts";

    public int countCustomers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<CustomerAccount> accountList = new ArrayList<>();
        CustomerAccount account = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_CUSTOMERS).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            account = document.toObject(CustomerAccount.class);
            accountList.add(account);
        }
        return accountList.size();
    }

    public int countMedicine(String pharmacyId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Medicine> medicineList = new ArrayList<>();
        Medicine medicine = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_MEDICINE).whereEqualTo("pharmacyId", pharmacyId).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            medicine = document.toObject(Medicine.class);
            medicineList.add(medicine);
        }
        return medicineList.size();
    }

}
