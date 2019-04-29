package com.example.text2;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.text2.adapter.VpAdapter;
import com.example.text2.fragment.AFragment;
import com.example.text2.fragment.BFragment;
import com.example.text2.fragment.CFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mList;
    private VpAdapter mAdapter;
    private AFragment mAFragment;
    /**
     * 我是Activity
     */
    private Button mBtn;
    private BFragment mBFragment;
    private CFragment mCFragment;
    private Handler hd = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    //郭栋
    private void initData() {
        mFragments = new ArrayList<>();
        mList = new ArrayList<>();
        mAFragment = new AFragment("顶顶顶顶");
        Bundle bundle = new Bundle();
        bundle.putString("data", "想死我!!!");
        mAFragment.setArguments(bundle);
        mFragments.add(mAFragment);
        mBFragment = new BFragment();
        mFragments.add(mBFragment);
        mCFragment = new CFragment();
        mFragments.add(mCFragment);
        mList.add("A");
        mList.add("B");
        mList.add("C");
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);

        mAdapter = new VpAdapter(getSupportFragmentManager(), mFragments, mList);
        mVp.setAdapter(mAdapter);
        mTab.setupWithViewPager(mVp);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        mBFragment.setOnClickListener(new BFragment.onClickListener() {
            @Override
            public void onClick(final String str) {
                mBtn.setText(str);
                mTab.getTabAt(2).select();
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCFragment.getData(str);
                    }
                },100);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                mAFragment.getData("alalalal");
                break;
        }
    }
}
