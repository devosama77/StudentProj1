package com.example.samy.studentproj1.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.samy.studentproj1.R;
import com.example.samy.studentproj1.adapters.TeachersFirebaseAdapter;
import com.example.samy.studentproj1.mdoel.Teacher;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


public class ShowTeachersFragment extends Fragment  {
     RecyclerView recyclerView;
     TeachersFirebaseAdapter teachersFirebaseAdapter;
     FirebaseFirestore db;
     ProgressBar progressBar;
    public ShowTeachersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.fragment_show_teachers, container, false);

          db=FirebaseFirestore.getInstance();
            setRecyclerView(view);
            return view;
    }

    @Override
    public void onStart() {
        super.onStart();
      teachersFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
       teachersFirebaseAdapter.stopListening();
    }


    public void setRecyclerView(View view){

        CollectionReference collection = db.collection("teachers");
        Query query=collection;
        recyclerView=view.findViewById(R.id.show_teachers_recyclerView);
        FirestoreRecyclerOptions options=new FirestoreRecyclerOptions.Builder<Teacher>()
                .setQuery(query,Teacher.class)
                .build();
        teachersFirebaseAdapter=new TeachersFirebaseAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(teachersFirebaseAdapter);
        teachersFirebaseAdapter.setOnTeacherClickListener(new TeachersFirebaseAdapter.OnTeacherClickListener() {
            @Override
            public void onTeacherClick(DocumentSnapshot documentSnapshot, int position) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(getResources()
                        .getString(R.string.teacher_preference), Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                String name = (String) documentSnapshot.get("name");
                String subject= (String) documentSnapshot.get("subject");
                String email= (String) documentSnapshot.get("email");
                String id=documentSnapshot.getId();
                edit.putString(getResources().getString(R.string.teacher_name),name);
                edit.putString(getResources().getString(R.string.teacher_subject),subject);
                edit.putString(getResources().getString(R.string.teacher_email),email);
                edit.putString(getResources().getString(R.string.teacher_id),id);
                edit.commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container
                ,new ShowQuestionsFragment()).commit();

            }
        });

    }


}
