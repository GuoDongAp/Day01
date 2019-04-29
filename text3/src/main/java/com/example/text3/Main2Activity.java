package com.example.text3;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.text3.api.MyServer;

import java.io.File;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 下载
     */
    private Button mBtn;
    private ProgressBar mPb1;
    private ProgressBar mPb2;
    private ProgressBar mPb3;
    private File mSd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mPb1 = (ProgressBar) findViewById(R.id.pb1);
        mPb2 = (ProgressBar) findViewById(R.id.pb2);
        mPb3 = (ProgressBar) findViewById(R.id.pb3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                initPermission();
                break;
        }
    }

    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            bindService();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            bindService();
        }
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.myBinder bind = (MyService.myBinder) service;
            bind.DownLoad(mPb1,mPb2,mPb3,mSd+"/"+"pp.apk");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void bindService() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mSd = Environment.getExternalStorageDirectory();
        }

        Intent intent = new Intent(Main2Activity.this, MyService.class);
        bindService(intent,connection ,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
