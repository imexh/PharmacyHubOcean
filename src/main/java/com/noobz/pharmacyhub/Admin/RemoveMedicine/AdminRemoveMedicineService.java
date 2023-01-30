package com.noobz.pharmacyhub.Admin.RemoveMedicine;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Medicine;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminRemoveMedicineService {

    private static final String COLLECTION_NAME = "medicineDetails";

    //service to get all medicine
    public List<Medicine> getAllMedicine() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Medicine> medicineList = new ArrayList<>();
        Medicine medicine = null;

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            medicine = document.toObject(Medicine.class);
            medicineList.add(medicine);
        }
        return medicineList;
    }

    //service to delete a medicine object in the database
    public boolean deleteMedicineDetails(@RequestParam String medicineId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(medicineId).delete();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
