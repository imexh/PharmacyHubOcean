package com.noobz.pharmacyhub.Admin.RemoveCustomers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.CustomerAccount;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminRemoveCustomersService {
    private static final String COLLECTION_NAME = "customerAccounts";

    //service to get all accounts
    public List<CustomerAccount> getAllAccounts() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<CustomerAccount> accountsList = new ArrayList<>();
        CustomerAccount account = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            account = document.toObject(CustomerAccount.class);
            accountsList.add(account);
        }
        return accountsList;
    }

    //service to delete an account object in the database
    public boolean deleteAccountDetails(@RequestParam String username) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(username).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
