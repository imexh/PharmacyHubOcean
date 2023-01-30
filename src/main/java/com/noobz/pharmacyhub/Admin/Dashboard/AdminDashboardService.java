package com.noobz.pharmacyhub.Admin.Dashboard;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.Advertisement;
import com.noobz.pharmacyhub.Classes.CustomerAccount;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminDashboardService {
    private static final String COLLECTION_PHARMACIES = "pharmacyAccounts";
    private static final String COLLECTION_CUSTOMERS = "customerAccounts";
    private static final String COLLECTION_MEDICINE = "medicineDetails";
    private static final String COLLECTION_ADS = "advertisementDetails";

    //service to count pharmacies
    public int countPharmacies() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Account> accountList = new ArrayList<>();
        Account account = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_PHARMACIES).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            account = document.toObject(Account.class);
            accountList.add(account);
        }
        return accountList.size();
    }

    //service to count customers
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

    //service to count medicine
    public int countMedicine() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Medicine> medicineList = new ArrayList<>();
        Medicine medicine = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_MEDICINE).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            medicine = document.toObject(Medicine.class);
            medicineList.add(medicine);
        }
        return medicineList.size();
    }

    //service to count ads
    public int countAdvertisements() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Advertisement> advertisementList = new ArrayList<>();
        Advertisement advertisement = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_ADS).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            advertisement = document.toObject(Advertisement.class);
            advertisementList.add(advertisement);
        }
        return advertisementList.size();
    }
}
