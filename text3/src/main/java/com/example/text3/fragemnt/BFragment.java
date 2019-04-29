package com.example.text3.fragemnt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.text3.R;


public class BFragment extends Fragment {


    private View view;
    private WebView mWeb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_b, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mWeb = (WebView) inflate.findViewById(R.id.web);

        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.loadUrl("https://www.baidu.com/");
    }
}
