package com.example.weilong.gankio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.webkit.WebSettings.PluginState.ON;

/**
 * 福利 adapter
 * Created by weilong on 2016/12/27.
 */

public class GirlRecyclerAdapter extends RecyclerView.Adapter<GirlRecyclerAdapter.GirlViewHolder> {
    private Context mContext;
    private List<DataList.ResultsBean> mResultsBeen;
    private LayoutInflater mLayoutInflater;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public GirlRecyclerAdapter(Context mContext, List<DataList.ResultsBean> mRes) {
        this.mContext = mContext;
        this.mResultsBeen = mRes;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<DataList.ResultsBean> mRes) {
        mResultsBeen.addAll(mRes);
    }

    public List<DataList.ResultsBean> getData() {
        return mResultsBeen;
    }


    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.adapter_girl, parent, false);
        return new GirlViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, int position) {
        DataList.ResultsBean bean = mResultsBeen.get(position);
        holder.mTvGirlWho.setText("发表人： " + bean.getWho());
        holder.mTvGirlPublishAt.setText("发表时间： " + bean.getPublishedAt());

        Picasso.with(mContext).load(bean.getUrl())
                .error(R.drawable.ic_feed_top)
                .into(holder.mImgGirlMap);

        if (mOnClickListener != null) {
            holder.mImgGirlMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();
                    mOnClickListener.setItemOnClickListener(view, pos);
                }
            });
        }
        if (mOnLongClickListener != null) {
            holder.mImgGirlMap.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnLongClickListener.setItemLongClickListener(view, position1);
                    return true;
                }
            });
        }

    }


    public void setItemOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener) {
        this.mOnLongClickListener = itemLongOnClickListener;
    }

    @Override
    public int getItemCount() {
        return mResultsBeen == null ? 0 : mResultsBeen.size();
    }

    public class GirlViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvGirlWho;
        private ImageView mImgGirlMap;
        private TextView mTvGirlPublishAt;

        public GirlViewHolder(View itemView) {
            super(itemView);
            mTvGirlWho = (TextView) itemView.findViewById(R.id.tv_girl_who);
            mImgGirlMap = (ImageView) itemView.findViewById(R.id.img_girl_map);
            mTvGirlPublishAt = (TextView) itemView.findViewById(R.id.tv_girl_publishAt);
        }
    }

}

