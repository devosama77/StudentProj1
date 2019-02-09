package com.example.samy.studentproj1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.samy.studentproj1.fragments.ShowQuestionsFragment;
import com.example.samy.studentproj1.fragments.ShowTeachersFragment;
import com.example.samy.studentproj1.mdoel.Teacher;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
   Toolbar toolbar;
    List<Teacher> teachers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("");
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_container,
                new ShowTeachersFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_menu_login:{

                break;
            }
            case R.id.toolbar_main_teachers:{
              getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container,
                      new ShowTeachersFragment()).commit();
              break;

            }

            case R.id.toolbar_main_questions:{
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container,
                        new ShowQuestionsFragment()).commit();
                break;

            }
        }
        return true;
    }

    public void getTeachers(){


    }
    public class TeachersAsyncTask extends AsyncTask<Void,Void,List<Teacher>>{

        @Override
        protected List<Teacher> doInBackground(Void... voids) {
            FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
            firebaseFirestore.collection("teachers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d:documents){
                        String name = d.get("name").toString();
                        String subject = d.get("subject").toString();
                        teachers.add(new Teacher());
                    }
                    String name = teachers.get(0).getName();
                    Log.d("osama","Name is :"+name);
                }
            });
            return teachers;
        }

        @Override
        protected void onPostExecute(List<Teacher> teachers) {
            super.onPostExecute(teachers);

           }
    }
}
