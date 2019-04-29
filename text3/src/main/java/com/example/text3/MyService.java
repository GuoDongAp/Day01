package com.example.text3;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.text3.util.ThreadManger;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MyService extends Service {
    private static final String TAG = MyService.class.getName();
    private String mUrl = "http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: " );
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: " );
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind:" );
        return new myBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: " );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class myBinder extends Binder{
        public void DownLoad(final ProgressBar pb1, final ProgressBar pb2, final ProgressBar pb3, final String path){
            ThreadManger.getManger().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpURLConnection con = (HttpURLConnection) new URL(mUrl).openConnection();
                        int max = con.getContentLength();
                        RandomAccessFile rw = new RandomAccessFile(path, "rw");
                        rw.setLength(max);

                        long block = max / 3;
                        for (int i = 0; i < 3; i++) {
                            long start = block * i;
                            long end = (i + 1) * block - 1;

                            if (i == 2) {
                                end = max-1;
                            }
                            donwLoad(path,max,start,end,i,pb1,pb2,pb3);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        private void donwLoad(final String path, int max, final long start, final long end, final int i, final ProgressBar pb1, final ProgressBar pb2, final ProgressBar pb3) {
            Log.d(TAG, "线程: " + i + ",下载范围:" + start + "-" + end);
            ThreadManger.getManger().execute(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sp = getSharedPreferences("load", MODE_PRIVATE);
                    long now = sp.getLong("now", 0l);
                    long count = start;
                    if (now > 0) {
                        count = now;
                    }
                    try {
                        HttpURLConnection con = (HttpURLConnection) new URL(mUrl).openConnection();
                        con.setRequestProperty("Range","bytes="+count+"-"+end);
                        if (con.getResponseCode() == 206) {

                            InputStream in = con.getInputStream();
                            RandomAccessFile rw = new RandomAccessFile(path, "rw");
                            rw.seek(count);

                            int len = -1;
                            byte[] bt = new byte[1024 * 10];
                            while ((len = in.read(bt)) != -1) {
                                rw.write(bt,0,len);

                                count += len;

                                Log.d(TAG, "线程: " + i + ",下载范围:" + count + "--" + end);
                                SharedPreferences.Editor edit = getSharedPreferences("load", MODE_PRIVATE).edit();
                                edit.putLong("now",count);
                                edit.commit();

                                switch (i) {
                                    case 0:
                                        pb1.setMax((int)end);
                                        pb1.setProgress((int)count);
                                        break;
                                    case 1:
                                        pb2.setMax((int)end);
                                        pb2.setProgress((int)count);
                                        break;
                                    case 2:
                                        pb3.setMax((int)end);
                                        pb3.setProgress((int)count);
                                        break;
                                }
                            }
                            in.close();
                            rw.close();

                            SharedPreferences.Editor edit = getSharedPreferences("load", MODE_PRIVATE).edit();
                            edit.putLong("now",0);
                            edit.commit();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
