package com.noobz.pharmacyhub.Pharmacy.AddMedicine;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AddMedicineService {
    private static final String COLLECTION_NAME = "medicineDetails";

    //service to search for medicine in database
    public boolean hasMedicine(String medicineId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(medicineId);

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

    //service to save a medicine object in the database
    public String saveMedicineDetails(Medicine medicine) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(medicine.getMedicineId()).set(medicine);

        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
