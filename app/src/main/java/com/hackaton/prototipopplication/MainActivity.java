package com.hackaton.prototipopplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hackaton.prototipopplication.Fragments.ConstructionFragment;
import com.hackaton.prototipopplication.Fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    public Fragment home = new HomeFragment();
    public Fragment construction = new ConstructionFragment();
    public ConnectivityManager connectivityManager;
    public NetworkInfo networkInfo;
    public FirebaseFirestore db;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        String androidId = getAndroidId().toString();
        bottomNav=findViewById(R.id.bottom_navigation);
        replacefragment(home,androidId,networkInfo);
        bottomNav.setOnItemSelectedListener(item -> {
            int id=item.getItemId();
            if (id == R.id.page_1) {
                replacefragment(home, androidId, networkInfo);
                return true;
            } else if (id == R.id.page_2) {
                replacefragment(construction, androidId, networkInfo);
                return true;
            } else if (id == R.id.page_3) {
                replacefragment(construction, androidId, networkInfo);
                return true;
            } else if (id == R.id.page_4) {
                replacefragment(construction, androidId, networkInfo);
                return true;
            }

            return true;
        });
//        last_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(value.isEmpty()){
//                    Toast.makeText(MainActivity.this, androidId,Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, value.getDocuments().get(0).get("Id").toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public String getAndroidId(){
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    public void replacefragment(Fragment newFragment, String androidId,NetworkInfo connect){
        Bundle bundle=new Bundle();
        bundle.putString("key", androidId);
        if(connect == null){
            {
                bundle.putString("key2","null");
            }
            }else{
            bundle.putString("key2", connect.toString());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        newFragment.setArguments(bundle);
        transaction.replace(R.id.frame_layout, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void iniciarNovaActivity(View view){
        Intent intent = new Intent(getApplicationContext(), CultivoActivity.class);
        startActivity(intent);
    }



}