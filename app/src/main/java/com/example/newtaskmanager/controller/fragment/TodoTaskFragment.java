package com.example.newtaskmanager.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private IRepository mRepository;

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
    }

    private void initViews() {
        mRecyclerViewTodoTask.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRepository = TaskRepository.getInstance();
        List<Task> tasks = mRepository.getTasks();
        mRecyclerViewTodoTask.setAdapter(new TaskAdapter(tasks, getActivity().getApplicationContext()));
    }
}