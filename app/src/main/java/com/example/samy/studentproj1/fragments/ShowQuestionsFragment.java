package com.example.samy.studentproj1.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.samy.studentproj1.R;
import com.example.samy.studentproj1.adapters.QuestionAdapter;
import com.example.samy.studentproj1.adapters.QuestionFirebaseAdapter;
import com.example.samy.studentproj1.mdoel.Question;
import com.example.samy.studentproj1.mdoel.Teacher;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowQuestionsFragment extends Fragment {
    SharedPreferences sharedPreferences;
    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
   // QuestionFirebaseAdapter questionFirebaseAdapter;
    QuestionAdapter questionAdapter;

    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    List<Question> questions=new ArrayList<>();
    List<Question> rightQuestions=new ArrayList<>();
    public ShowQuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_show_questions, container, false);
         progressBar=view.findViewById(R.id.progress_show_question);


           synchronized (this){
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       setRecyclerViewQuestions(view);
                   }
               },0);
           }




        return view;
    }
    public void setRecyclerViewQuestions(View view){
        progressBar=view.findViewById(R.id.progress_show_question);
        sharedPreferences = getContext().getSharedPreferences(getResources()
                .getString(R.string.teacher_preference), Context.MODE_PRIVATE);
        progressBar.setVisibility(View.VISIBLE);
        String id = sharedPreferences.getString(getResources()
                .getString(R.string.teacher_id), "NoId");

        CollectionReference collection = firebaseFirestore.collection("teachers")
                .document(id).collection("questions");
        recyclerView=view.findViewById(R.id.recycler_view_show_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        collection.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressBar.setVisibility(View.GONE);
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:documents){
                    Question question=new Question();
                    question.setQuestion(d.getString("question"));
                    question.setAnswerText1(d.getString("answerText1"));
                    question.setAnswerText2(d.getString("answerText2"));
                    question.setAnswerText3(d.getString("answerText3"));
                    question.setAnswerText4(d.getString("answerText4"));
                    question.setAnswer1(d.getBoolean("answer1"));
                    question.setAnswer2(d.getBoolean("answer2"));
                    question.setAnswer3(d.getBoolean("answer3"));
                    question.setAnswer4(d.getBoolean("answer4"));
                    rightQuestions.add(new Question(d.getBoolean("answer1"),
                            d.getBoolean("answer2"),
                            d.getBoolean("answer3"),
                            d.getBoolean("answer4")));
                    questions.add(question);

                }
                questionAdapter=new QuestionAdapter(questions,getActivity());
                recyclerView.setAdapter(questionAdapter);
            }
        });
        checkResult(view,rightQuestions);
    }

    public void checkResult(View view, final List<Question> rQuetions){
        floatingActionButton=view.findViewById(R.id.floating_action_add_question);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for(int i=0;i<1;i++){
                    Log.d("data2","Right1: "+rQuetions.get(i).isAnswer1());
                    Log.d("data2","Right2: "+rQuetions.get(i).isAnswer2());
                    Log.d("data2","Right3: "+rQuetions.get(i).isAnswer3());
                    Log.d("data2","Right4: "+rQuetions.get(i).isAnswer4());
                }

                int count=0;
              int result=0;
              int resultInt=0;
              List<Question> checkQuestions=questionAdapter.getQuestions();

              for (int i=0;i<rQuetions.size();i++){
                  Question rightQuestion = rQuetions.get(i);
                  boolean r1=checkQuestions.get(i).isAnswer1()==rQuetions.get(i).isAnswer1();
                  boolean r2=checkQuestions.get(i).isAnswer2()==rQuetions.get(i).isAnswer2();
                  boolean r3=checkQuestions.get(i).isAnswer3()==rQuetions.get(i).isAnswer3();
                  boolean r4=checkQuestions.get(i).isAnswer4()==rQuetions.get(i).isAnswer4();
                  Log.d("data1",r1+" "+r2+" "+r3+" "+r4);
                  Log.d("data1","Right1: "+rQuetions.get(i).isAnswer1()
                          +"\n Check1: "+checkQuestions.get(i).isAnswer1());

                  Log.d("data1","Right2: "+rightQuestion.isAnswer2()
                          +"\n Check2: "+checkQuestions.get(i).isAnswer2());

                  Log.d("data1","Right3: "+rightQuestion.isAnswer3()
                          +"\n Check3: "+checkQuestions.get(i).isAnswer3());

                  Log.d("data1","Right4: "+rightQuestion.isAnswer4()
                          +"\n Check4: "+checkQuestions.get(i).isAnswer4());
                  if(r1&r2&r3&r4){
                           count=count+1;
                  }else {}
                 result=count*100/checkQuestions.size();
                 resultInt = (int) result;
              }
          Toast.makeText(getActivity(),"The result is "+resultInt+" %",Toast.LENGTH_LONG).show();
           }
       });

    }
}
