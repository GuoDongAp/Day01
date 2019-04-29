package com.example.text1.presenter;

import com.example.text1.bean.DbBean;
import com.example.text1.bean.FlBean;
import com.example.text1.contract.HomeContract;

/**
 * Created by dell on 2019/4/26.
 */

public class HomePresenter implements HomeContract.Presenter, HomeContract.CallBack {
    private HomeContract.Model mModel;
    private HomeContract.View mView;

    public HomePresenter(HomeContract.Model model, HomeContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void getData() {
        if (mModel != null) {
            mModel.getData(this);
        }
    }

    @Override
    public void insert(DbBean bean) {
        if (mModel != null) {
            mModel.insert(bean,this);
        }
    }

    @Override
    public void onSuccess(FlBean bean) {
        if (mView != null) {
            mView.onSuccess(bean);
        }
    }

    @Override
    public void onFial(String msg) {
        if (mView != null) {
            mView.onFial(msg);
        }
    }

    @Override
    public void onInsert(String msg) {
        if (mView != null) {
            mView.onInsert(msg);
        }
    }
}
