package com.example.text1.api;

import com.example.text1.bean.FlBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by dell on 2019/4/26.
 */

public interface MyServer {
    String mUrl = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";

    @GET("8")
    Observable<FlBean> getData();
}
