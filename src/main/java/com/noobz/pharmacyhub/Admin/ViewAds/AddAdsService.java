package com.noobz.pharmacyhub.Admin.ViewAds;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Advertisement;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AddAdsService {

    private static final String COLLECTION_NAME = "advertisementDetails";

    //service to save an advertisement object in the database
    public String saveAdvertisement(Advertisement advertisement) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(advertisement.getId()).set(advertisement);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    //service to search for advertisement in database
    public boolean hasAdvertisement(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return true;
        } else {
            return false;
        }

    }
}
