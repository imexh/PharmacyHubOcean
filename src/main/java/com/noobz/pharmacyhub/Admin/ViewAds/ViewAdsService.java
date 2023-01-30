package com.noobz.pharmacyhub.Admin.ViewAds;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Advertisement;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ViewAdsService {
    private static final String COLLECTION_NAME = "advertisementDetails";

    //service to get all ads
    public List<Advertisement> getAllAds() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Advertisement> advertisementList = new ArrayList<>();
        Advertisement advertisement = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            advertisement = document.toObject(Advertisement.class);
            advertisementList.add(advertisement);
        }
        return advertisementList;
    }

    //service to delete an ad object in the database
    public boolean deleteAdvertisementDetails(@RequestParam String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
