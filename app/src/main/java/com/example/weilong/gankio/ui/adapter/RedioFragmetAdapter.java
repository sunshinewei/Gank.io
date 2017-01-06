package com.example.weilong.gankio.ui.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;
import com.example.weilong.gankio.utils.MetricUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * 休息视频 adapter
 * Created by weilong on 2016/12/27.
 */

public class RedioFragmetAdapter extends RecyclerView.Adapter<RedioFragmetAdapter.RedioViewHolder> {
    private Context mContext;
    private List<DataList.ResultsBean> mResultsBeen;
    private LayoutInflater mLayoutInflater;
    private MediaPlayer mMediaPlayer;
    private OnClickListener mOnClickListener;
private OnLongClickListener mOnLongClickListener;

    public RedioFragmetAdapter(Context mContext, List<DataList.ResultsBean> mRes) {
        this.mContext = mContext;
        this.mResultsBeen = mRes;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<DataList.ResultsBean> mRes){
        this.mResultsBeen.addAll(mRes);
    }
    public List<DataList.ResultsBean>  getData(){
        return mResultsBeen;
    }


    @Override
    public RedioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.adapter_redio, parent, false);
        return new RedioViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(final RedioViewHolder holder, final int position) {
        View itemView = holder.itemView;
        itemView.setTranslationY(MetricUtils.getScrHeight(mContext));
        itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(300)
                .start();
        DataList.ResultsBean bean = mResultsBeen.get(position);
        Picasso.with(mContext).load(bean.getUrl())
                .error(R.drawable.icon_video)
                .placeholder(R.drawable.icon_video)
                .into(holder.mImgRedioShow);
        holder.mTvRedioWho.setText(bean.getDesc());

        holder.mButRedioControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redioPlay(holder, position);
                holder.mMediaPlayer.start();
                holder.mProRedioProgess.setProgress(holder.mMediaPlayer.getCurrentPosition());
            }
        });


        if (mOnClickListener!=null){
            holder.mButRedioControl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position1 = holder.getAdapterPosition();
                    mOnClickListener.setItemOnClickListener(view,position1);
                }
            });
        }
    }

    /**
     * suferView播放
     *
     * @param mHolder
     */
    private void redioPlay(final RedioViewHolder mHolder, int position) {
        final DataList.ResultsBean bean = mResultsBeen.get(position);
        mHolder.mTvRedioWho.setText(bean.getDesc());

        final SurfaceHolder mSurface = mHolder.mSurRedioShow.getHolder();
        mSurface.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurface.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mHolder.mMediaPlayer.reset();
                try {
                    mHolder.mMediaPlayer.setDataSource(bean.getUrl());
                    mHolder.mMediaPlayer.setDisplay(mSurface);
                    mHolder.mMediaPlayer.prepare();
                    mHolder.mProRedioProgess.setMax(mHolder.mMediaPlayer.getDuration());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mHolder.mMediaPlayer.stop();
            }
        });
    }

    public void setItemOnClickListener(OnClickListener itemOnClickListener){
        this.mOnClickListener=itemOnClickListener;
    }
    public void setItemLongOnClickListener(OnLongClickListener itemLongOnClickListener){
        this.mOnLongClickListener=itemLongOnClickListener;
    }

    @Override
    public int getItemCount() {
        return mResultsBeen == null ? 0 : mResultsBeen.size();
    }

    public class RedioViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvRedioWho;
        private SurfaceView mSurRedioShow;
        private ImageButton mButRedioControl;
        private SeekBar mProRedioProgess;
        private MediaPlayer mMediaPlayer;
        private ImageView mImgRedioShow;

        public RedioViewHolder(View itemView) {
            super(itemView);
            mTvRedioWho = (TextView) itemView.findViewById(R.id.tv_redio_who);
            mSurRedioShow = (SurfaceView) itemView.findViewById(R.id.sur_redio_show);
            mButRedioControl = (ImageButton) itemView.findViewById(R.id.but_redio_control);
            mProRedioProgess = (SeekBar) itemView.findViewById(R.id.pro_redio_progess);
            mImgRedioShow = (ImageView) itemView.findViewById(R.id.img_redio_show);
            mMediaPlayer = new MediaPlayer();
        }
    }
}
