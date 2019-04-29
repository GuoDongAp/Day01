package com.example.dell.day01;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 联系人
     */
    private Button mBt1;
    /**
     * 短信
     */
    private Button mBtn2;
    /**
     * 音乐
     */
    private Button mBtn3;
    /**
     * 视频
     */
    private Button mBtn4;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.Btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
        mLl = (LinearLayout) findViewById(R.id.ll);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt1:
                initPContacts();
                break;
            case R.id.Btn2:
                initPSms();
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }

    private void initPSms() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            readSms();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},200);
        }
    }

    private void initPContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            readContacts();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
                readContacts();
            } else if (requestCode == 200) {
                readSms();
            }
        }
    }

    private void readSms() {
        Cursor query = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                String data = query.getString(query.getColumnIndex(Telephony.Sms.DATE));
                String body = query.getString(query.getColumnIndex(Telephony.Sms.BODY));

                TextView textView = new TextView(this);
                textView.setText("data:"+data+"   body:"+body);
                mLl.addView(textView);
            }
        }
    }

    private void readContacts() {
        ContentResolver resolver = getContentResolver();
        Cursor query = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                TextView textView = new TextView(this);
                textView.setText("姓名:"+name+"   电话号:"+number);
                mLl.addView(textView);
            }
        }
    }
}
