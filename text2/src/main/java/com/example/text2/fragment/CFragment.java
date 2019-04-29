package com.example.text2.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.text2.R;

public class CFragment extends Fragment {

    private View view;
    /**
     * Hello blank fragment
     */
    private TextView mTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_c, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        mTv = (TextView) inflate.findViewById(R.id.tv);
    }

    public void getData(String str){
        mTv.setText(str);
    }
}
