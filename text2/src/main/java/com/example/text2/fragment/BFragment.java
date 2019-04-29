package com.example.text2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.text2.R;

public class BFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_b, container, false);

        if (mOnClickListener != null) {
            mOnClickListener.onClick("欧拉拉!!!");
        }
        return inflate;
    }

    private onClickListener mOnClickListener;

    public void setOnClickListener(onClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface onClickListener{
        void onClick(String str);
    }

}
