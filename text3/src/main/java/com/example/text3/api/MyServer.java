package com.example.text3.api;

import com.example.text3.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by dell on 2019/4/28.
 */

public interface MyServer {
    String mUrl = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";

    @GET("5")
    Observable<Bean> getData();
}
