package com.hackaton.prototipopplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CultivoActivity extends AppCompatActivity {
    public FirebaseFirestore db;
    public ConnectivityManager connectivityManager;
    public NetworkInfo networkInfo;
    EditText areaFazenda;
    EditText desejaPlantar;
    EditText rotacaoPlantio;
    EditText manejoResiduos;
    EditText plantioColheita;
    EditText irrigacaoFertilizacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);
        areaFazenda = findViewById(R.id.areaFazenda);
        desejaPlantar = findViewById(R.id.desejaPlantar);
        rotacaoPlantio = findViewById(R.id.rotacaoPlantio);
        manejoResiduos = findViewById(R.id.manejoResiduos);
        plantioColheita = findViewById(R.id.plantioColheita);
        irrigacaoFertilizacao = findViewById(R.id.irrigacaoFertilizacao);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null) {
            //Toast.makeText(MainActivity.this,getAndroidId(),Toast.LENGTH_SHORT).show();
            db = FirebaseFirestore.getInstance();
        }else{
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();
            db = FirebaseFirestore.getInstance();
            db.setFirestoreSettings(settings);

        }


    }

    public void insertPlantacao(View view){
        String androidId = getAndroidId().toString();
        Plantacao plantacao = new Plantacao();
        plantacao.setAreaFazenda(areaFazenda.getText().toString());
        plantacao.setDesejaPlantar(desejaPlantar.getText().toString());
        plantacao.setIrrigacaoFertilizacao(irrigacaoFertilizacao.getText().toString());
        plantacao.setManejoResiduos(manejoResiduos.getText().toString());
        plantacao.setRotacaoPlantio(rotacaoPlantio.getText().toString());
        plantacao.setPlantioColheita(plantioColheita.getText().toString());
        db.collection("users").document(androidId).set(plantacao).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(),plantacao.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public String getAndroidId(){
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }
}