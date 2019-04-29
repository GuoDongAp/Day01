package com.example.text3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.text3.bean.Bean;
import com.example.text3.R;

import java.util.ArrayList;

/**
 * Created by dell on 2019/4/28.
 */

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {
    private final Context mContext;
    private ArrayList<Bean.ResultsBean> mList;

    public RlvAdapter(Context context, ArrayList<Bean.ResultsBean> list) {

        mContext = context;
        mList = list;
    }

    public void setList(ArrayList<Bean.ResultsBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bean.ResultsBean ba= mList.get(position);
        holder.mDesc.setText(ba.getDesc());
        holder.mUrl.setText(ba.getUrl());
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(15));
        Glide.with(mContext).load(ba.getUrl()).apply(options).placeholder(R.mipmap.ic_launcher).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIv;
        TextView mDesc;
        TextView mUrl;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mIv = (ImageView) itemView.findViewById(R.id.iv);
            this.mDesc = (TextView) itemView.findViewById(R.id.desc);
            this.mUrl = (TextView) itemView.findViewById(R.id.url);
        }
    }
}
