package com.noobz.pharmacyhub.Pharmacy.Pharmacy;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class viewPharmacyService {
    private static final String COLLECTION_NAME = "pharmacyAccounts";

    //service to get pharmacy details for a specific registrationId
    public Account getPharmacyDetails(String registrationId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(registrationId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Account account = null;

        if(document.exists())
        {
            account = document.toObject(Account.class);
            return account;
        }
        else
        {
            return null;
        }

    }
}
