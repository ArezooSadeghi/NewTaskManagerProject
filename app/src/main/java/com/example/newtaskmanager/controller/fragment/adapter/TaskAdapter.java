package com.example.newtaskmanager.controller.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtaskmanager.R;
import com.example.newtaskmanager.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> mTasks;
    private Context mContext;

    public TaskAdapter(List<Task> tasks, Context context) {
        mTasks = tasks;
        mContext = context;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.taskholder, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.bindViews(mTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewDescription;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_title);
            mTextViewDescription = itemView.findViewById(R.id.txt_description);
        }

        public void bindViews(Task task) {
            mTextViewTitle.setText(task.getTitle());
            mTextViewDescription.setText(task.getDescription());
        }
    }
}
