package com.example.samy.studentproj1.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.samy.studentproj1.R;
import com.example.samy.studentproj1.mdoel.Question;

import java.util.ArrayList;
import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM=0;
    private final int VIEW_TYPE_LOADING=1;
    Activity activity;
    List<Question> questions;
    List<Question> myQuestions=new ArrayList<>();
        public QuestionAdapter(List<Question> questions,Activity activity){
            this.activity=activity;
             this.questions=questions;
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType==VIEW_TYPE_ITEM){
            View view=LayoutInflater.from(activity).inflate(R.layout.question_layout,viewGroup,false);
            QuestionViewer questionViewer=new QuestionViewer(view);
            return questionViewer;
        }else if(viewType==VIEW_TYPE_LOADING){
            View view=LayoutInflater.from(activity).inflate(R.layout.item_loading,viewGroup,
                    false);
            return new LoadingItemView(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(viewHolder instanceof QuestionViewer){
            QuestionViewer itemViewHolder= (QuestionViewer) viewHolder;
            itemViewHolder.questionView.setText(questions.get(i).getQuestion());
            itemViewHolder.answer1.setText(questions.get(i).getAnswerText1());
            itemViewHolder.answer2.setText(questions.get(i).getAnswerText2());
            itemViewHolder.answer3.setText(questions.get(i).getAnswerText3());
            itemViewHolder.answer4.setText(questions.get(i).getAnswerText4());
            itemViewHolder.answer1.setChecked(false);
            itemViewHolder.answer2.setChecked(false);
            itemViewHolder.answer3.setChecked(false);
            itemViewHolder.answer4.setChecked(false);
             Question question=new Question();
            question.setAnswer1(questions.get(i).isAnswer1());
            question.setAnswer2(questions.get(i).isAnswer2());
            question.setAnswer3(questions.get(i).isAnswer3());
            question.setAnswer4(questions.get(i).isAnswer4());
            myQuestions.add(question);

        }else if(viewHolder instanceof LoadingItemView){
            LoadingItemView loadingItemView= (LoadingItemView) viewHolder;
            loadingItemView.progressBar.setIndeterminate(true);

        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class LoadingItemView extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingItemView(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progress_id);
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

    @Override
    public int getItemViewType(int position) {
        return questions==null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }



    public List<Question> getQuestions(){
        return questions;
    }
}
