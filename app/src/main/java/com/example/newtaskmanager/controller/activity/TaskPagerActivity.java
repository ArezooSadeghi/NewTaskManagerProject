package com.example.newtaskmanager.controller.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newtaskmanager.R;
import com.example.newtaskmanager.controller.fragment.DoingTaskFragment;
import com.example.newtaskmanager.controller.fragment.DoneTaskFragment;
import com.example.newtaskmanager.controller.fragment.TodoTaskFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TaskPagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskpager);
        findViews();
        initViews();
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

    private void findViews() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
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
}