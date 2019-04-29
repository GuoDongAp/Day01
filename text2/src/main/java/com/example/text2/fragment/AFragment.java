package com.example.text2.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.text2.R;


@SuppressLint("ValidFragment")
public class AFragment extends Fragment {


    private final String mStr;
    private View view;
    private Button mBtn;
    private String mData;
    private Button mBtn1;
    private Button mBtn2;


    public AFragment(String str) {
        mStr = str;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_a, container, false);
        Bundle bundle = getArguments();
        mData = bundle.getString("data");
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mBtn = (Button) inflate.findViewById(R.id.btn);
        mBtn.setText(mStr);
        mBtn1 = (Button) inflate.findViewById(R.id.btn1);
        mBtn1.setText(mData);
        mBtn2 = (Button) inflate.findViewById(R.id.btn2);
    }

    public void getData(String str) {
        mBtn2.setText(str);
    }
}
