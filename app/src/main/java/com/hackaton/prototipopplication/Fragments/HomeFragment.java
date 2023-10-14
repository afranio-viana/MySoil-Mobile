package com.hackaton.prototipopplication.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.hackaton.prototipopplication.MainActivity;
import com.hackaton.prototipopplication.Plantacao;
import com.hackaton.prototipopplication.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView imageInformation;
    private View rootview;
    private TextView titleNews;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String networkInfo =getArguments().getString("key2");



        //Toast.makeText(MainActivity.this,String.valueOf(networkInfo),Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this,getAndroidId(),Toast.LENGTH_SHORT).show();
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
        String androidId = getArguments().getString("key");

        Query last_query= db.collection("informations").orderBy("createdAt", Query.Direction.DESCENDING).limit(1);
//        last_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(!value.isEmpty()){
//
//                }
//            }
//        });

        last_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(!value.isEmpty()){
                        String imageUrl = value.getDocuments().get(0).get("img").toString();
                        String strTitleNews = value.getDocuments().get(0).get("title").toString();
                        //Toast.makeText(getContext(), imageUrl,Toast.LENGTH_SHORT).show();
                        imageInformation = rootview.findViewById(R.id.informationImage);
                        titleNews = rootview.findViewById(R.id.titleNews);
                        titleNews.setText(strTitleNews);
                        Picasso.get().load(imageUrl).into(imageInformation);
                }
            }
        });
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_home, container, false);
        return rootview;
    }

}