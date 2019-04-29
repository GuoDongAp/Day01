package com.example.text1.adapter;

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
import com.example.text1.R;
import com.example.text1.bean.FlBean;

import java.util.ArrayList;

/**
 * Created by dell on 2019/4/26.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private ArrayList<FlBean.ResultsBean> mList;

    public HomeAdapter(Context context, ArrayList<FlBean.ResultsBean> list) {

        mContext = context;
        mList = list;
    }

    public ArrayList<FlBean.ResultsBean> getList() {
        return mList;
    }

    public void setList(ArrayList<FlBean.ResultsBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        return new RlvViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RlvViewHolder hd = (RlvViewHolder) holder;
        final FlBean.ResultsBean bean = mList.get(position);
        hd.mDesc.setText(bean.getDesc());
        hd.mUrl.setText(bean.getUrl());
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(15));
        Glide.with(mContext).load(bean.getUrl()).apply(options).placeholder(R.mipmap.ic_launcher).into(hd.mIv);

        hd.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(bean,position);
                }
            }
        });

        hd.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListenr != null) {
                    mOnLongClickListenr.onLongClick(bean,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RlvViewHolder extends RecyclerView.ViewHolder {
        ImageView mIv;
        TextView mDesc;
        TextView mUrl;
        public RlvViewHolder(View itemView) {
            super(itemView);
            this.mIv = (ImageView) itemView.findViewById(R.id.iv);
            this.mDesc = (TextView) itemView.findViewById(R.id.desc);
            this.mUrl = (TextView) itemView.findViewById(R.id.url);
        }
    }

    private onClickListener mOnClickListener;
    private onLongClickListenr mOnLongClickListenr;

    public void setOnClickListener(onClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListenr(onLongClickListenr onLongClickListenr) {
        mOnLongClickListenr = onLongClickListenr;
    }

    public interface onClickListener{
        void onClick(FlBean.ResultsBean bean,int position);
    }

    public interface onLongClickListenr{
        void onLongClick(FlBean.ResultsBean bean,int position);
    }

}
