package com.noobz.pharmacyhub.Pharmacy.Settings;

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
public class PharmacySettingsService {
    private static final String COLLECTION_NAME = "pharmacyAccounts";

    public Account getAccountDetails(String accountId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(accountId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Account acc = null;

        if(document.exists())
        {
            acc = document.toObject(Account.class);
            return acc;
        }
        else
        {
            return null;
        }

    }

    public String updateAccountDetails(Account account) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(account.getRegistrationId()).set(account);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
