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

import static android.webkit.WebSettings.PluginState.ON;

/**
 * Created by weilong on 2016/12/27.
 */

public class ExtendFragmentAdapter extends RecyclerView.Adapter<ExtendFragmentAdapter.ExtendViewHodler> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<DataList.ResultsBean> mResultsBeen;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    public ExtendFragmentAdapter(){

    }

    public void setData(List<DataList.ResultsBean> mRes){
        this.mResultsBeen.addAll(mRes);
    }

    public List<DataList.ResultsBean> getData(){
        return mResultsBeen;
    }


    public ExtendFragmentAdapter(Context mConext,List<DataList.ResultsBean> mRes){
        this.mContext=mConext;
        this.mResultsBeen=mRes;
        mLayoutInflater=LayoutInflater.from(mConext);
    }
    @Override
    public ExtendViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = mLayoutInflater.inflate(R.layout.adapter_extend, parent, false);
        return new ExtendViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(final ExtendViewHodler holder, int position) {
        DataList.ResultsBean bean = mResultsBeen.get(position);
        holder.mTvExtendDesc.setText(bean.getDesc());
        holder.mTvExtendType.setText("类型： "+bean.getType());
        holder.mTvExtendWho.setText("via "+bean.getWho());

        holder.mTvExtendDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position1 = holder.getAdapterPosition();
                mOnClickListener.setItemOnClickListener(view,position1);
            }
        });
        if (mOnLongClickListener!=null){
            holder.mTvExtendDesc.setOnLongClickListener(new View.OnLongClickListener() {
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
    public class ExtendViewHodler extends RecyclerView.ViewHolder{
        private TextView mTvExtendDesc;
        private TextView mTvExtendType;
        private TextView mTvExtendWho;
        public ExtendViewHodler(View itemView) {
            super(itemView);
            mTvExtendDesc = (TextView) itemView.findViewById(R.id.tv_extend_desc);
            mTvExtendType = (TextView) itemView.findViewById(R.id.tv_extend_type);
            mTvExtendWho = (TextView) itemView.findViewById(R.id.tv_extend_who);
        }
    }
}
