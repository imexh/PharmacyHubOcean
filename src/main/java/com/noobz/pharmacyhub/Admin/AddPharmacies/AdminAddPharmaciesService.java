package com.noobz.pharmacyhub.Admin.AddPharmacies;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AdminAddPharmaciesService {
    private static final String COLLECTION_NAME = "pharmacyAccounts";

    //service to save an account object in the database
    public String saveAccountDetails(Account account) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(account.getRegistrationId()).set(account);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    //service to search for account in database
    public boolean hasAccount(String registrationId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(registrationId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
