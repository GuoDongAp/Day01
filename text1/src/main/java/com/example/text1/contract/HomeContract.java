package com.example.text1.contract;

import com.example.text1.bean.DbBean;
import com.example.text1.bean.FlBean;

/**
 * Created by dell on 2019/4/26.
 */

public interface HomeContract {
    interface Model {
        void getData(HomeContract.CallBack back);
        void insert(DbBean bean, HomeContract.CallBack back);
    }

    interface View {
        void onSuccess(FlBean bean);
        void onFial(String msg);
        void onInsert(String msg);
    }

    interface Presenter {
        void getData();
        void insert(DbBean bean);
    }
    interface CallBack{
        void onSuccess(FlBean bean);
        void onFial(String msg);
        void onInsert(String msg);
    }
}
