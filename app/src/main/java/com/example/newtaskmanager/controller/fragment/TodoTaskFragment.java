package com.example.newtaskmanager.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtaskmanager.R;
import com.example.newtaskmanager.controller.fragment.adapter.TaskAdapter;
import com.example.newtaskmanager.model.Task;
import com.example.newtaskmanager.repository.IRepository;
import com.example.newtaskmanager.repository.TaskRepository;

import java.util.List;

public class TodoTaskFragment extends Fragment {
    private RecyclerView mRecyclerViewTodoTask;
    private LinearLayout mLayoutShowEmptyRecyclerview;
    private IRepository mRepository;
    private TaskAdapter mTaskAdapter;

    public TodoTaskFragment() {
    }

    public static TodoTaskFragment newInstance() {
        TodoTaskFragment fragment = new TodoTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo_task, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void findViews(View view) {
        mRecyclerViewTodoTask = view.findViewById(R.id.todotask_recyclerview);
        mLayoutShowEmptyRecyclerview = view.findViewById(R.id.layout_empty_recyclerview);
    }

    private void initViews() {
        mRecyclerViewTodoTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRepository = TaskRepository.getInstance();
        List<Task> tasks = mRepository.getTasks();
        if (!(tasks.size() == 0)) {
            if (mTaskAdapter == null) {
                mRecyclerViewTodoTask.setAdapter(new TaskAdapter(tasks, getActivity()));
            } else {
                mTaskAdapter.setTasks(tasks);
                mTaskAdapter.notifyDataSetChanged();
            }
        } else {
            mLayoutShowEmptyRecyclerview.setVisibility(View.VISIBLE);
        }
    }
}