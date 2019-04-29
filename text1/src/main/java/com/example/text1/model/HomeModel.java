package com.example.text1.model;

import com.example.text1.api.MyServer;
import com.example.text1.bean.DbBean;
import com.example.text1.bean.FlBean;
import com.example.text1.contract.HomeContract;
import com.example.text1.util.DbUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2019/4/26.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public void getData(final HomeContract.CallBack back) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.mUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(MyServer.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlBean bean) {
                        if (back != null) {
                            if (bean != null) {
                                back.onSuccess(bean);
                            }else {
                                back.onFial("错误!!!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (back != null) {
                            back.onFial(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void insert(DbBean bean, HomeContract.CallBack back) {
        long ll = DbUtil.getmUtil().insert(bean);
        if (back != null) {
            if (ll > 0) {
                back.onInsert("添加成功!!!");
            }else {
                back.onInsert("添加失败!!!");
            }
        }
    }
}
