package com.example.newtaskmanager.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newtaskmanager.R;
import com.example.newtaskmanager.controller.fragment.AddNewTaskDialogFragment;
import com.example.newtaskmanager.controller.fragment.DoingTaskFragment;
import com.example.newtaskmanager.controller.fragment.DoneTaskFragment;
import com.example.newtaskmanager.controller.fragment.TodoTaskFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TaskPagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton mActionButtonAdd;
    public static final String TAG = "TPA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskpager);
        findViews();
        setListeners();
        initViews();
    }

    private void findViews() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        mActionButtonAdd = findViewById(R.id.fab_addnewtask);
    }

    private void setListeners() {
        mActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTaskDialogFragment addNewTaskDialogFragment = AddNewTaskDialogFragment.
                        newInstance();
                addNewTaskDialogFragment.show(getSupportFragmentManager(), TAG);
            }
        });
    }

    private void initViews() {
        mViewPager.setAdapter(new TaskPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setText(R.string.done);
                                break;
                            case 1:
                                tab.setText(R.string.doing);
                                break;
                            default:
                                tab.setText(R.string.todo);
                                break;
                        }
                    }
                });
        tabLayoutMediator.attach();
    }

    private class TaskPagerAdapter extends FragmentStateAdapter {
        private static final int NUMBER_OF_TASK = 3;

        public TaskPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return DoneTaskFragment.newInstance();
                case 1:
                    return DoingTaskFragment.newInstance();
                default:
                    return TodoTaskFragment.newInstance();
            }
        }

        @Override
        public int getItemCount() {
            return NUMBER_OF_TASK;
        }
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TaskPagerActivity.class);
        return intent;
    }
}