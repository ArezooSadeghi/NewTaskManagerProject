package com.example.newtaskmanager.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.newtaskmanager.R;
import com.example.newtaskmanager.model.Task;
import com.example.newtaskmanager.repository.IRepository;
import com.example.newtaskmanager.repository.TaskRepository;

public class AddNewTaskDialogFragment extends DialogFragment {
    private EditText mEditTextTitle, mEditTextDescription;
    private Button mButtonSave;
    private IRepository mRepository;

    public AddNewTaskDialogFragment() {
    }

    public static AddNewTaskDialogFragment newInstance() {
        AddNewTaskDialogFragment fragment = new AddNewTaskDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_new_task_dialog, null);
        findViews(view);
        setListeners();
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).
                setView(view).create();
        return dialog;
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.txt_title_newtask);
        mEditTextDescription = view.findViewById(R.id.txt_description_newtask);
        mButtonSave = view.findViewById(R.id.btn_savenewtask);
    }

    private void setListeners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task();
                task.setTitle(mEditTextTitle.getText().toString());
                task.setDescription(mEditTextDescription.getText().toString());
                mRepository = TaskRepository.getInstance();
                mRepository.insert(task);
                dismiss();
            }
        });
    }
}