package com.example.samy.studentproj1.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.samy.studentproj1.R;
import com.example.samy.studentproj1.mdoel.Question;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;


public class QuestionFirebaseAdapter
        extends FirestoreRecyclerAdapter<Question,RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM=0;
    private final int VIEW_TYPE_LOADING=1;
    Activity activity;
    Question question=new Question();
          List<Question> questions=new ArrayList<>();

    public QuestionFirebaseAdapter(@NonNull FirestoreRecyclerOptions<Question> options,
                                   Activity activity) {
        super(options);
        this.activity=activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType==VIEW_TYPE_ITEM){
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_layout,viewGroup,false);
            QuestionViewer questionViewer=new QuestionViewer(view);
            return questionViewer;
        }else if(viewType==VIEW_TYPE_LOADING){
            View view=LayoutInflater.from(activity).inflate(R.layout.item_loading,viewGroup,
                    false);
            return new LoadingItemView(view);
        }
        return  null;

    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,
                                    int position, @NonNull Question model) {
        if(viewHolder instanceof QuestionViewer){
            QuestionViewer itemViewHolder= (QuestionViewer) viewHolder;
            itemViewHolder.questionView.setText(model.getQuestion());
            itemViewHolder.answer1.setText(model.getAnswerText1());
            itemViewHolder.answer2.setText(model.getAnswerText2());
            itemViewHolder.answer3.setText(model.getAnswerText3());
            itemViewHolder.answer4.setText(model.getAnswerText4());
            itemViewHolder.answer1.setChecked(false);
            itemViewHolder.answer2.setChecked(false);
            itemViewHolder.answer3.setChecked(false);
            itemViewHolder.answer4.setChecked(false);
            Question question=new Question();
            question.setAnswer1(model.isAnswer1());
            question.setAnswer2(model.isAnswer2());
            question.setAnswer3(model.isAnswer3());
            question.setAnswer4(model.isAnswer4());
            questions.add(question);
            question=model;

        }else if(viewHolder instanceof LoadingItemView){
            LoadingItemView loadingItemView= (LoadingItemView) viewHolder;
            loadingItemView.progressBar.setIndeterminate(true);

        }

    }

    public class QuestionViewer extends RecyclerView.ViewHolder {
        CheckBox answer1,answer2,answer3,answer4;
        TextView questionView;
        public QuestionViewer(@NonNull View itemView) {
            super(itemView);
            questionView=itemView.findViewById(R.id.text_view_question_show);
            answer1=itemView.findViewById(R.id.checkbox_q_show_answer1);
            answer2=itemView.findViewById(R.id.checkbox_q_show_answer2);
            answer3=itemView.findViewById(R.id.checkbox_q_show_answer3);
            answer4=itemView.findViewById(R.id.checkbox_q_show_answer4);

            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked=((CheckBox)v).isChecked();
                    if(checked){
                        questions.get(getAdapterPosition()).setAnswer1(true);
                    }else {
                       questions.get(getAdapterPosition()).setAnswer1(false);
                    }

                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked=((CheckBox)v).isChecked();
                    if(checked){
                        questions.get(getAdapterPosition()).setAnswer2(true);
                    }else {
                        questions.get(getAdapterPosition()).setAnswer2(false);
                    }
                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked=((CheckBox)v).isChecked();
                    if(checked){
                        questions.get(getAdapterPosition()).setAnswer3(true);
                    }else {
                        questions.get(getAdapterPosition()).setAnswer3(false);
                    }
                }
            });
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked=((CheckBox)v).isChecked();
                    if(checked){
                        questions.get(getAdapterPosition()).setAnswer4(true);
                    }else {
                        questions.get(getAdapterPosition()).setAnswer4(false);
                    }
                }
            });
        }
    }
    public List<Question> getQuestions(){
        return questions;
    }

    class LoadingItemView extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingItemView(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progress_id);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return question==null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }
}
