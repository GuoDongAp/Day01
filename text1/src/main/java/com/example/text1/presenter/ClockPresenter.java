package com.example.text1.presenter;

import com.example.text1.bean.DbBean;
import com.example.text1.contract.ClockContract;

import java.util.List;

/**
 * Created by dell on 2019/4/26.
 */

public class ClockPresenter implements ClockContract.Presenter, ClockContract.CallBack {
    private ClockContract.Model mModel;
    private ClockContract.View mView;


    public ClockPresenter(ClockContract.Model model, ClockContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void query() {
        if (mModel != null) {
            mModel.query(this);
        }
    }

    @Override
    public void delete(DbBean bean) {
        if (mModel != null) {
            mModel.delete(bean,this);
        }
    }

    @Override
    public void update(DbBean bean) {
        if (mModel != null) {
            mModel.update(bean,this);
        }
    }

    @Override
    public void onQuerySuccess(List<DbBean> list) {
        if (mView != null) {
            mView.onQuerySuccess(list);
        }
    }

    @Override
    public void onQueryFial(String msg) {
        if (mView != null) {
            mView.onQueryFial(msg);
        }
    }

    @Override
    public void onDelete(String msg) {
        if (mView != null) {
            mView.onDelete(msg);
        }
    }

    @Override
    public void onUpdate(String msg) {
        if (mView != null) {
            mView.onUpdate(msg);
        }
    }
}
