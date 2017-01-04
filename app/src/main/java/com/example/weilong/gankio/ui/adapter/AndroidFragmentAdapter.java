package com.example.weilong.gankio.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;

import java.util.List;

/**
 * Created by weilong on 2016/12/27.
 */

public class AndroidFragmentAdapter extends RecyclerView.Adapter<AndroidFragmentAdapter.AndroidViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<DataList.ResultsBean> mResultsBeen;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public AndroidFragmentAdapter() {

    }

    public void setData(List<DataList.ResultsBean> mRes){
        if (mResultsBeen!=null){
            mResultsBeen.addAll(mRes);
        }
    }
    public List<DataList.ResultsBean> getData(){
        return mResultsBeen;
    }

    public AndroidFragmentAdapter(Context mConext, List<DataList.ResultsBean> mRes) {
        this.mContext=mConext;
        this.mResultsBeen = mRes;
        mLayoutInflater = LayoutInflater.from(mConext);
    }

    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.adapter_android, parent, false);
        return new AndroidViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final AndroidViewHolder holder, int position) {
        DataList.ResultsBean bean = mResultsBeen.get(position);
        holder.mTvAndroidDesc.setText(bean.getDesc());
        holder.mTvAndroidPublishAt.setText("发表时间： "+bean.getPublishedAt());
        holder.mTvAndroidWho.setText("发表人："+bean.getWho());

        holder.mTvAndroidDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                mOnClickListener.setItemOnClickListener(view,pos);
            }
        });
        if (mOnLongClickListener!=null){
            holder.mTvAndroidDesc.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnLongClickListener.setItemLongClickListener(view,position1);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mResultsBeen==null ? 0: mResultsBeen.size();
    }

    public void setItemOnClickListener( OnClickListener mMyOnClickOnListener){
        this.mOnClickListener=mMyOnClickOnListener;
    }

    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener){
        this.mOnLongClickListener=itemLongOnClickListener;
    }


    public class AndroidViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvAndroidDesc;
        private TextView mTvAndroidWho;
        private TextView mTvAndroidPublishAt;

        public AndroidViewHolder(View itemView) {
            super(itemView);
            mTvAndroidDesc = (TextView) itemView.findViewById(R.id.tv_android_desc);
            mTvAndroidWho = (TextView) itemView.findViewById(R.id.tv_android_who);
            mTvAndroidPublishAt = (TextView) itemView.findViewById(R.id.tv_android_publishAt);
        }
    }
}
