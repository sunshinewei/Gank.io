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
 * 前端 adapter
 * Created by weilong on 2016/12/27.
 */

public class UIFragmentAdapter extends RecyclerView.Adapter<UIFragmentAdapter.UIViewHolder> {
    private Context mContext;
    private List<DataList.ResultsBean> mResultsBeen;
    private LayoutInflater mLayoutInflater;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public UIFragmentAdapter(Context mContext,List<DataList.ResultsBean> mRes){
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
    public UIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.adapter_ui, parent, false);

        return new UIViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final UIViewHolder holder, int position) {
        DataList.ResultsBean bean = mResultsBeen.get(position);
        View itemView = holder.itemView;
        itemView.setTranslationY(MetricUtils.getScrHeight(mContext));
        itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(300)
                .start();
        holder.mTvUiWho.setText("发表人："+bean.getWho());
        holder.mTvAndroidPublishAt.setText("发表时间： "+bean.getPublishedAt());
        holder.mTvUiDesc.setText(bean.getDesc());

        if (mOnClickListener!=null){

            holder.mTvUiDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnClickListener.setItemOnClickListener(view,position1);
                }
            });
        }

        if (mOnLongClickListener!=null){
            holder.mTvUiDesc.setOnLongClickListener(new View.OnLongClickListener() {
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

    public void setItemOnClickListener(OnClickListener itemOnClickListener){
        this.mOnClickListener=itemOnClickListener;
    }
    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener){
        this.mOnLongClickListener=itemLongOnClickListener;
    }
    public class UIViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvUiWho;
        private TextView mTvUiDesc;
        private TextView mTvAndroidPublishAt;

        public UIViewHolder(View itemView) {
            super(itemView);
            mTvUiWho = (TextView) itemView.findViewById(R.id.tv_ui_who);
            mTvUiDesc = (TextView) itemView.findViewById(R.id.tv_ui_desc);
            mTvAndroidPublishAt = (TextView) itemView.findViewById(R.id.tv_android_publishAt);
        }
    }
}
