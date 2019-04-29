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
import com.example.text1.adapter.ClockAdapter;
import com.example.text1.bean.DbBean;
import com.example.text1.contract.ClockContract;
import com.example.text1.model.ClockModel;
import com.example.text1.presenter.ClockPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClockFragment extends Fragment implements ClockContract.View {


    private View view;
    private RecyclerView mRlv;
    private ClockPresenter mPresenter;
    private List<DbBean> mList = new ArrayList<>();
    private ClockAdapter mAdapter;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            initData();
        }else {
            mList.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_clock, container, false);
        mPresenter = new ClockPresenter(new ClockModel(), this);
        initView(inflate);
        return inflate;
    }

    private void initData() {
        mPresenter.query();
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ClockAdapter(getContext(), mList);
        mRlv.setAdapter(mAdapter);


        mAdapter.setOnClickListener(new ClockAdapter.onClickListener() {
            @Override
            public void onClick(DbBean bean, int position) {
                mPresenter.delete(bean);
                mAdapter.getList().remove(bean);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.setOnLongClickListenr(new ClockAdapter.onLongClickListenr() {
            @Override
            public void onLongClick(DbBean bean, int position) {
                bean.setDase("阿花");
                mPresenter.update(bean);
                mAdapter.getList().set(position,bean);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onQuerySuccess(List<DbBean> list) {
        mList.addAll(list);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onQueryFial(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
