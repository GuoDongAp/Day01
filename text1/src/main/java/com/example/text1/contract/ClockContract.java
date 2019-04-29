package com.example.text1.contract;

import com.example.text1.bean.DbBean;

import java.util.List;

/**
 * Created by dell on 2019/4/26.
 */

public interface ClockContract {
    interface Model {
        void query(CallBack back);
        void delete(DbBean bean,CallBack back);
        void update(DbBean bean,CallBack back);
    }

    interface View {
        void onQuerySuccess(List<DbBean> list);
        void onQueryFial(String msg);
        void onDelete(String msg);
        void onUpdate(String msg);
    }

    interface Presenter {
        void query();
        void delete(DbBean bean);
        void update(DbBean bean);
    }
    interface CallBack{
        void onQuerySuccess(List<DbBean> list);
        void onQueryFial(String msg);
        void onDelete(String msg);
        void onUpdate(String msg);
    }
}
