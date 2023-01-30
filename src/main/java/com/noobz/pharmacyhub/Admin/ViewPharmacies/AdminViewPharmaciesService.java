package com.noobz.pharmacyhub.Admin.ViewPharmacies;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminViewPharmaciesService {

    private static final String COLLECTION_NAME = "pharmacyAccounts";

    //service to get all accounts
    public List<Account> getAllAccounts() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Account> accountsList = new ArrayList<>();
        Account account = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            account = document.toObject(Account.class);
            accountsList.add(account);
        }
        return accountsList;
    }

    //service to delete an account object in the database
    public boolean deleteAccountDetails(@RequestParam String registrationId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(registrationId).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
