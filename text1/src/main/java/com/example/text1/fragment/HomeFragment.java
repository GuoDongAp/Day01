package com.example.text1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.text1.R;
import com.example.text1.adapter.HomeAdapter;
import com.example.text1.bean.DbBean;
import com.example.text1.bean.FlBean;
import com.example.text1.contract.HomeContract;
import com.example.text1.model.HomeModel;
import com.example.text1.presenter.HomePresenter;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeContract.View {

    private View view;
    private RecyclerView mRlv;
    private HomePresenter mPresenter;
    private ArrayList<FlBean.ResultsBean> mList;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(new HomeModel(), this);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        mPresenter.getData();
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(getContext(), mList);
        mRlv.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new HomeAdapter.onClickListener() {
            @Override
            public void onClick(FlBean.ResultsBean bean, int position) {
                mPresenter.insert(new DbBean(null,bean.getUrl(),bean.getDesc()));
            }
        });

    }

    @Override
    public void onSuccess(FlBean bean) {
        mList.addAll(bean.getResults());
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFial(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsert(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
