package com.example.weilong.gankio.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
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

import static android.R.attr.duration;

/**
 * 首页 adapter
 * Created by weilong on 2016/12/22.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {
    private Context mContext;
    private List<DataList.ResultsBean> mList;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public MainRecyclerAdapter(Context mContext, List<DataList.ResultsBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
}

    public void setData(List<DataList.ResultsBean> mRes){
        this.mList.addAll(mRes);
    }

    public List<DataList.ResultsBean> getData(){
        return mList;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_content_adapter, parent, false);
        return new MainViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        DataList.ResultsBean resultsBean = mList.get(position);
        View itemView = holder.itemView;
        itemView.setTranslationY(MetricUtils.getScrHeight(mContext));
        itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(300)
                .start();
        holder.mTvMainDesc.setText(resultsBean.getDesc());
        holder.mTvMainType.setText(resultsBean.getType());
        holder.mTvMainWho.setText(resultsBean.getWho());

        if (mOnClickListener!=null){
            holder.mTvMainDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnClickListener.setItemOnClickListener(view,position1);
                }
            });
        }

        if (mOnLongClickListener!=null){
            holder.mTvMainDesc.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnLongClickListener.setItemLongClickListener(view,position1);
                    return true;
                }
            });
        }
    }

    public void setItemOnclickListener(OnClickListener onclickListener){
        this.mOnClickListener=onclickListener;

    }

    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener){
        this.mOnLongClickListener=itemLongOnClickListener;
    }
    @Override
    public int getItemCount() {
        return mList==null ? 0 : mList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvMainDesc;
        private TextView mTvMainType;
        private TextView mTvMainWho;
        public MainViewHolder(View itemView) {
            super(itemView);
            mTvMainDesc = (TextView) itemView.findViewById(R.id.tv_main_desc);
            mTvMainType = (TextView) itemView.findViewById(R.id.tv_main_type);
            mTvMainWho = (TextView) itemView.findViewById(R.id.tv_main_who);
        }
    }

}
