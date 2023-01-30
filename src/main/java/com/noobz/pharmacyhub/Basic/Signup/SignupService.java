package com.noobz.pharmacyhub.Basic.Signup;

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
public class SignupService {
    private static final String COLLECTION_NAME = "pharmacyAccounts";

    //service to save an account object in the database
    public String saveAccountDetails(Account account) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(account.getRegistrationId()).set(account);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    //service to get account details for a specific registrationId
    public Account getAccountDetails(String registrationId) throws ExecutionException, InterruptedException {
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

    //service to update an account object in the database
    public String updateAccountDetails(Account account) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(account.getRegistrationId()).set(account);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    //service to delete an account object in the database
    public String deleteAccountDetails(String registrationId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(registrationId).delete();
        return "Deleted successfully";
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
