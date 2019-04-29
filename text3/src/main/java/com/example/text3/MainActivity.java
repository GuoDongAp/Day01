package com.example.text3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.text3.adapter.VpAdapter;
import com.example.text3.fragemnt.AFragment;
import com.example.text3.fragemnt.BFragment;
import com.example.text3.fragemnt.CFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mVp;
    private TabLayout mTab;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mList;
    private VpAdapter mAdapter;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mList = new ArrayList<>();

        mFragments.add(new AFragment());
        mFragments.add(new BFragment());
        mFragments.add(new CFragment());
        mList.add("A");
        mList.add("B");
        mList.add("B");

        mAdapter = new VpAdapter(getSupportFragmentManager(), mFragments, mList);
        mVp.setAdapter(mAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);

        setSupportActionBar(mToolbar);

        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pop:
                        showPop();
                        break;
                    case R.id.bord:
                        sendBroad();
                        break;
                    case R.id.phone:
                        callPhone();
                        break;
                }
                return false;
            }
        });
        mLl = (LinearLayout) findViewById(R.id.ll);
    }

    private void callPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            call();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            call();
        }
    }

    @SuppressLint("MissingPermission")
    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void sendBroad() {
        Intent ggg = new Intent("ggg");
        ggg.setComponent(new ComponentName(getPackageName(), "com.example.text3.MyReceiver"));
        sendBroadcast(ggg);
    }

    private void showPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop, null);
        Button btn = inflate.findViewById(R.id.btn);
        final PopupWindow pop = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);


        inflate.findViewById(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        pop.setAnimationStyle(R.style.PopAnimation);


        //pop.showAsDropDown(mToolbar);
        pop.showAtLocation(mLl, Gravity.NO_GRAVITY,0,0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlite();
            }
        });
    }

    private void showAlite() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("标题")
                .setMessage("是否还怀念?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "通知");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showNoti();
        return super.onOptionsItemSelected(item);
    }

    private void showNoti() {
        NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channlId = "suibian";
        String channlName = "二货";

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channlId, channlName, NotificationManager.IMPORTANCE_DEFAULT);
            manger.createNotificationChannel(channel);
        }
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        PendingIntent in = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, channlId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("标题")
                .setContentText("白菜降价le!!!")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(in)
                .build();

        manger.notify(1, notification);
    }
}
