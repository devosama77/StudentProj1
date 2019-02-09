package com.example.samy.studentproj1.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samy.studentproj1.R;
import com.example.samy.studentproj1.mdoel.Teacher;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class TeachersFirebaseAdapter
        extends FirestoreRecyclerAdapter<Teacher,TeachersFirebaseAdapter.TeacherView> {
    OnTeacherClickListener listener;
    public TeachersFirebaseAdapter(@NonNull FirestoreRecyclerOptions<Teacher> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherView holder, int position, @NonNull Teacher model) {
        holder.nameView.setText(model.getName());
        holder.subjectView.setText(model.getSubject());
    }

    @NonNull
    @Override
    public TeacherView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(i==getItemCount()-1){
              view = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.progress_bar_layout, viewGroup, false);
            }else {
                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.teachers_layout,viewGroup,false);

            }
        TeacherView teacherView=new TeacherView(view);
        return teacherView;
    }

    public class TeacherView extends RecyclerView.ViewHolder{
        TextView nameView,subjectView;
       public TeacherView(@NonNull View itemView) {
           super(itemView);
           nameView=itemView.findViewById(R.id.teacher_layout_name);
           subjectView=itemView.findViewById(R.id.teacher_layout_subject);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   listener.onTeacherClick(getSnapshots().getSnapshot(position),position);
               }
           });
       }
   }
   public void setOnTeacherClickListener(OnTeacherClickListener listener){
             this.listener=listener;
   }
   public interface OnTeacherClickListener{
        public void onTeacherClick(DocumentSnapshot documentSnapshot,int position);
   }

    @Override
    public int getItemCount() {
        return getSnapshots().size();
    }
}
