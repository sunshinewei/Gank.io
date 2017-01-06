package com.example.weilong.gankio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;
import com.example.weilong.gankio.utils.MetricUtils;

import java.util.List;

/**
 * ios adapter
 * Created by weilong on 2016/12/27.
 */

public class IOSFragmentAdapter extends RecyclerView.Adapter<IOSFragmentAdapter.IOSViewHolder> {
    private Context mContext;
    private List<DataList.ResultsBean> mResultsBeen;
    private LayoutInflater mLayoutInflater;
    private OnClickListener mOnClickListener;
private OnLongClickListener mOnLongClickListener;
    public IOSFragmentAdapter(Context mContext,List<DataList.ResultsBean> mRes){
        this.mContext=mContext;
        this.mResultsBeen=mRes;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public void setData(List<DataList.ResultsBean> mRes){
        this.mResultsBeen.addAll(mRes);
    }

    public List<DataList.ResultsBean> getData(){
        return mResultsBeen;
    }

    @Override
    public IOSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.adapter_ios, parent, false);
        return new IOSViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final IOSViewHolder holder, int position) {
        DataList.ResultsBean bean = mResultsBeen.get(position);
        View itemView = holder.itemView;
        itemView.setTranslationY(MetricUtils.getScrHeight(mContext));
        itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(300)
                .start();
        holder.mTvIosWho.setText("发表人： "+bean.getWho());
        holder.mTvIosDesc.setText(bean.getDesc());
        holder.mTvIosPublishAt.setText("发布时间: "+bean.getPublishedAt());

        if (mOnClickListener!=null){
            holder.mTvIosDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnClickListener.setItemOnClickListener(view,position1);
                }
            });
        }
        if (mOnLongClickListener!=null){
            holder.mTvIosDesc.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnLongClickListener.setItemLongClickListener(view,position1);
                    return true;
                }
            });
        }
    }
    public void setItemOnClickListener(OnClickListener itemOnClickListener){
        this.mOnClickListener=itemOnClickListener;
    }
    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener){
        this.mOnLongClickListener=itemLongOnClickListener;
    }

    @Override
    public int getItemCount() {
        return mResultsBeen==null ? 0: mResultsBeen.size();
    }
    public class IOSViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvIosWho;
        private TextView mTvIosDesc;
        private TextView mTvIosPublishAt;

        public IOSViewHolder(View itemView) {
            super(itemView);
            mTvIosWho = (TextView) itemView.findViewById(R.id.tv_ios_who);
            mTvIosDesc = (TextView) itemView.findViewById(R.id.tv_ios_desc);
            mTvIosPublishAt = (TextView) itemView.findViewById(R.id.tv_ios_publishAt);
        }
    }
}
