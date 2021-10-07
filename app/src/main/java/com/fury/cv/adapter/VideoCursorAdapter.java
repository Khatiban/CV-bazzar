package com.fury.cv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fury.cv.R;
import com.fury.cv.model.VideoData;
import com.fury.cv.view.DialogUtils;
import com.fury.cv.view.Format_list;
import com.fury.cv.view.VideoListActivity;
import com.fury.cv.view.create_list;
import com.fury.cv.view.cut_list;
import com.fury.cv.view.join_list;
import com.fury.cv.view.logo_list;
import com.fury.cv.view.mp3_list;
import com.fury.cv.view.voice_list;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import java.util.ArrayList;

public class VideoCursorAdapter extends BaseAdapter {
    ImageLoader imgLoader;
    private LayoutInflater infalter;
    private final Context mContext;
    ArrayList<VideoData> videoList;
    int f18w;
    int f17h;
    int mov;

    /* renamed from: com.video.compressop.adapter.VideoCursorAdapter.1 */
    class C10381 implements BitmapProcessor {
        C10381() {
        }

        public Bitmap process(Bitmap bmp) {
            return Bitmap.createScaledBitmap(bmp, 150, 150, false);
        }
    }

    /* renamed from: com.video.compressop.adapter.VideoCursorAdapter.2 */
    class C10392 implements OnClickListener {
        private final /* synthetic */ int val$position;

        C10392(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            if (mov == 1){
                ((VideoListActivity) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 2){
                ((Format_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 3){
                ((mp3_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 4){
                ((voice_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 5){
                ((cut_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 6){
                ((join_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 7){
                ((logo_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }else if (mov == 8){
                ((create_list) VideoCursorAdapter.this.mContext).callVideo(this.val$position);
            }
        }
    }

    private class ViewHolder {
        ImageView ivVideoThumb;
        RelativeLayout rl_main;
        TextView tvVideoName;

        private ViewHolder() {
        }
    }

    public VideoCursorAdapter(int a,int b,Context c, ArrayList<VideoData> vdata, ImageLoader imgloader,int ac) {
        this.videoList = new ArrayList();
        this.mContext = c;
        this.imgLoader = imgloader;
        this.infalter = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.videoList.addAll(vdata);
        f18w = a;
        mov = ac;
        f17h = b;
        f18w = f18w < 1 ? 720 : f18w;
        f17h = f17h < 1 ? 1280 : f17h;
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

    public void removeItem(int position) {
        this.videoList.remove(position);
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            if (mov == 3){
                convertView = this.infalter.inflate(R.layout.myvideo_row_v_mp3, null);
            }else {
                convertView = this.infalter.inflate(R.layout.myvideo_row, null);
            }
            holder = new ViewHolder();
            if (mov == 3){
            }else {
                holder.ivVideoThumb = (ImageView) convertView.findViewById(R.id.image_preview);
                holder.rl_main = (RelativeLayout) convertView.findViewById(R.id.rl_main);
                holder.rl_main.setLayoutParams(new LayoutParams((f18w / 2) - 4, (f18w / 2) - 4));
            }
            holder.tvVideoName = (TextView) convertView.findViewById(R.id.file_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (mov == 3){
        }else {
            this.imgLoader.displayImage(((VideoData) this.videoList.get(position)).VideoUri.toString(), holder.ivVideoThumb, new Builder().showImageForEmptyUri((int) R.color.trans).cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).bitmapConfig(Bitmap.Config.ARGB_8888).delayBeforeLoading(50).postProcessor(new C10381()).displayer(new SimpleBitmapDisplayer()).build());
        }
        convertView.setOnClickListener(new C10392(position));
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mov == 1){
                    DialogUtils.showfileDialog(VideoListActivity.act,new C03982(position));
                }else if (mov == 2){
                    DialogUtils.showfileDialog(Format_list.act,new C03982(position));
                }else if (mov == 3){
                    DialogUtils.showfileDialog(mp3_list.act,new C03982(position));
                }else if (mov == 4){
                    DialogUtils.showfileDialog(voice_list.act,new C03982(position));
                }else if (mov == 5){
                    DialogUtils.showfileDialog(cut_list.act,new C03982(position));
                }else if (mov == 6){
                    DialogUtils.showfileDialog(join_list.act,new C03982(position));
                }else if (mov == 7){
                    DialogUtils.showfileDialog(logo_list.act,new C03982(position));
                }else if (mov == 8){
                    DialogUtils.showfileDialog(create_list.act,new C03982(position));
                }
                return false;
            }
        });
        holder.tvVideoName.setText(((VideoData) this.videoList.get(position)).videoName);
        return convertView;
    }



    class C03982 implements DialogUtils.DialogBtnClickListener_file {
        int s;

        C03982(int w) {
            s = w;
        }

        public void onPositiveClick_1() {
            if (mov == 1){
                DialogUtils.showDeleteDialog(VideoListActivity.act, new C03981(s));
            }else if (mov == 2){
                DialogUtils.showDeleteDialog(Format_list.act, new C03981(s));
            }else if (mov == 3){
                DialogUtils.showDeleteDialog(mp3_list.act, new C03981(s));
            }else if (mov == 4){
                DialogUtils.showDeleteDialog(voice_list.act, new C03981(s));
            }else if (mov == 5){
                DialogUtils.showDeleteDialog(cut_list.act, new C03981(s));
            }else if (mov == 6){
                DialogUtils.showDeleteDialog(join_list.act, new C03981(s));
            }else if (mov == 7){
                DialogUtils.showDeleteDialog(logo_list.act, new C03981(s));
            }else if (mov == 8){
                DialogUtils.showDeleteDialog(create_list.act, new C03981(s));
            }
        }
        public void onPositiveClick_2() {
            if (mov == 1){
                ((VideoListActivity) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 2){
                ((Format_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 3){
                ((mp3_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 4){
                ((voice_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 5){
                ((cut_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 6){
                ((join_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 7){
                ((logo_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }else if (mov == 8){
                ((create_list) VideoCursorAdapter.this.mContext).shareTmpFile(s);
            }
        }
        public void onPositiveClick_3() {
            if (mov == 1){
                ((VideoListActivity) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 2){
                ((Format_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 3){
                ((mp3_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 4){
                ((voice_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 5){
                ((cut_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 6){
                ((join_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 7){
                ((logo_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }else if (mov == 8){
                ((create_list) VideoCursorAdapter.this.mContext).detailFile(s);
            }
        }
    }

    class C03981 implements DialogUtils.DialogBtnClickListener {
        private final /* synthetic */ int val$position;

        C03981(int i) {
            this.val$position = i;
        }

        public void onPositiveClick() {
            if (mov == 1){
                ((VideoListActivity) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 2){
                ((Format_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 3){
                ((mp3_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 4){
                ((voice_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 5){
                ((cut_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 6){
                ((join_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 7){
                ((logo_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }else if (mov == 8){
                ((create_list) VideoCursorAdapter.this.mContext).deleteTmpFile(val$position);
            }
        }
    }

}
