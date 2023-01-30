package com.noobz.pharmacyhub.Pharmacy.Inventory;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PharmacyInventoryService {
    private static final String COLLECTION_NAME = "medicineDetails";

    //service to get all
    public List<Medicine> getAllMedicine(@RequestParam String pharmacyId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Medicine> medicineList = new ArrayList<>();
        Medicine medicine = null;

        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("pharmacyId", pharmacyId);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            medicine = document.toObject(Medicine.class);
            medicineList.add(medicine);
        }
        return medicineList;
    }

    //service to delete an ad object in the database
    public boolean deleteMedicine(@RequestParam String medicineId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(medicineId).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    //service to get medicine details for a specific registrationId
    public Medicine getMedicineDetails(String medicineID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(medicineID);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Medicine medicine = null;

        if(document.exists())
        {
            medicine = document.toObject(Medicine.class);
            return medicine;
        }
        else
        {
            return null;
        }

    }
}
