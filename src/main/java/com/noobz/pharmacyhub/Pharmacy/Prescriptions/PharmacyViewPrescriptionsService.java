package com.noobz.pharmacyhub.Pharmacy.Prescriptions;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.noobz.pharmacyhub.Classes.Account;
import com.noobz.pharmacyhub.Classes.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PharmacyViewPrescriptionsService {

    private static final String COLLECTION_NAME = "prescriptionDetails";

    public List<Prescription> getAllPrescriptions(@RequestParam String pharmacyId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription = null;

        Query query = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("pharmacyId", pharmacyId);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            prescription = document.toObject(Prescription.class);
            prescriptionList.add(prescription);
        }

        Query query1 = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("status", "open");
        ApiFuture<QuerySnapshot> future1 = query1.get();

        List<QueryDocumentSnapshot> documents1 = future1.get().getDocuments();
        for (QueryDocumentSnapshot document : documents1) {
            prescription = document.toObject(Prescription.class);
            prescriptionList.add(prescription);
        }

        return prescriptionList;
    }

    public boolean deletePrescription(@RequestParam String prescriptionId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try
        {
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(prescriptionId).delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Prescription getPrescriptionDetails(String prescriptionId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(prescriptionId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Prescription prescription = null;

        if(document.exists())
        {
            prescription = document.toObject(Prescription.class);
            return prescription;
        }
        else
        {
            return null;
        }

    }

    public String updatePrescriptionDetails(Prescription prescription) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(prescription.getPrescriptionId()).set(prescription);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
