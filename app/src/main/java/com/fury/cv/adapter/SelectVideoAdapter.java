package com.fury.cv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fury.cv.R;
import com.fury.cv.model.VideoData;
import com.fury.cv.view.SelectVideoActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import java.util.ArrayList;

public class SelectVideoAdapter extends BaseAdapter {
    ImageLoader imgLoader;
    private LayoutInflater infalter;
    private final Context mContext;
    ArrayList<VideoData> videoList;
    ArrayList<VideoData> videoList_ser;

    int tedad;

    /* renamed from: com.video.compressop.adapter.SelectVideoAdapter.1 */
    class C10361 implements BitmapProcessor {
        C10361() {
        }

        public Bitmap process(Bitmap bmp) {
            return Bitmap.createScaledBitmap(bmp, 100, 100, false);
        }
    }

    /* renamed from: com.video.compressop.adapter.SelectVideoAdapter.2 */
    class C10372 implements OnClickListener {
        private final /* synthetic */ int val$position;

        C10372(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            ((SelectVideoActivity) SelectVideoAdapter.this.mContext).callVideo(this.val$position);
        }
    }

    private class ViewHolder {
        ImageView ivVideoThumb;
        TextView tvDuration;
        TextView tvVideoName;

        private ViewHolder() {
        }
    }

    public SelectVideoAdapter(Context c, ArrayList<VideoData> vdata, ImageLoader imgloader) {
        this.videoList = new ArrayList();
        this.mContext = c;
        this.imgLoader = imgloader;
        this.infalter = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.videoList.addAll(vdata);
        this.videoList_ser = videoList;
    }

    public int getCount() {
        return this.videoList.size();
    }

    public Object getItem(int position) {
        return this.videoList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.infalter.inflate(R.layout.select_video_row, null);
            holder = new ViewHolder();
            holder.ivVideoThumb = (ImageView) convertView.findViewById(R.id.image_preview);
            holder.tvVideoName = (TextView) convertView.findViewById(R.id.file_name);
            holder.tvDuration = (TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        this.imgLoader.displayImage(((VideoData) this.videoList.get(position)).VideoUri.toString(), holder.ivVideoThumb, new Builder().showImageForEmptyUri(0).cacheInMemory(true).showStubImage(R.color.trans).cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.ARGB_8888).delayBeforeLoading(10).postProcessor(new C10361()).displayer(new SimpleBitmapDisplayer()).build());
        convertView.setOnClickListener(new C10372(position));
        if (position % 2 == 0) {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.trans_list));
        } else {
            convertView.setBackgroundColor(-1);
        }
        holder.tvVideoName.setText(((VideoData) this.videoList.get(position)).videoName);
        holder.tvDuration.setText(((VideoData) this.videoList.get(position)).Duration);
        tedad = position;
        return convertView;
    }

}
