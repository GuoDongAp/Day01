package com.example.text1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.text1.adapter.VpAdapter;
import com.example.text1.fragment.ClockFragment;
import com.example.text1.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mList;
    private VpAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ClockFragment());
        mList = new ArrayList<>();
        mList.add("首页");
        mList.add("收藏");
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);

        mAdapter = new VpAdapter(getSupportFragmentManager(), mFragments, mList);
        mVp.setAdapter(mAdapter);
        mTab.setupWithViewPager(mVp);
    }
}
