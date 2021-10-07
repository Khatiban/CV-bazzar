package com.fury.cv.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.adapter.VideoCursorAdapter;
import com.fury.cv.model.VideoData;
import com.fury.cv.util.ContentUtill;
import com.google.firebase.crash.FirebaseCrash;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by fury on 5/17/2017.
 */
public class cut_list extends Activity {


    private static final int BACK_FROM_VIDEOSHARE = 99;
    GridView VideogridView;
    VideoCursorAdapter adapter;
    ImageView btnBack,btn_plus;
    ImageLoader imgLoader;
    TextView textnot;
    private PowerManager pm;
    ArrayList<VideoData> videoList;
    public static cut_list act;
    private PowerManager.WakeLock wl;

    /* renamed from: com.video.compressop.view.VideoListActivity.1 */
    class C10541 implements View.OnClickListener {
        C10541() {
        }

        public void onClick(View arg0) {
            cut_list.this.onBackPressed();
        }
    }

    @SuppressLint({"NewApi"})
    private class loadVideo extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog pd;

        private loadVideo() {
            this.pd = null;
        }

        protected void onPreExecute() {
            this.pd = new ProgressDialog(cut_list.this);
            this.pd.setMessage("\u0635\u0628\u0631 \u06a9\u0646\u06cc\u062f...");
            this.pd.setCancelable(false);
            this.pd.show();
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(cut_list.this.getVideoList());
        }

        protected void onPostExecute(Boolean result) {
            this.pd.dismiss();
            if (result.booleanValue()) {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int f100w = dm.widthPixels;
                int f99h = dm.heightPixels;
                cut_list.this.adapter = new VideoCursorAdapter(f100w,f99h,cut_list.this, cut_list.this.videoList, cut_list.this.imgLoader,5);
                cut_list.this.VideogridView.setAdapter(cut_list.this.adapter);
            }
        }
    }

    public cut_list() {
        this.videoList = new ArrayList();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_video_list);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(6, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("1"));
        }
        FindbyId();
        this.videoList.clear();
        act = this;
        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        TextView tvt = (TextView) findViewById(R.id.tvt2);
        textnot.setText("ویدیویی بریده نشده \n برای برش ویدیو بر روی دکمه + کلیک کنید و ویدیو مورد نظر را انتخاب کنید");
        textnot.setTypeface(face);
        tvt.setText("برش ویدیو");
        tvt.setTypeface(face);

        initImageLoader();
        new loadVideo().execute();

        FirebaseCrash.log("log 1");
    }

    private void FindbyId() {
        VideogridView = (GridView) findViewById(R.id.VideogridView);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btn_plus = (ImageView) findViewById(R.id.btn_plus);
        textnot = (TextView) findViewById(R.id.textnot);
        btnBack.setOnClickListener(new C10541());
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(cut_list.this,SelectVideoActivity.class);
                n.putExtra("activ",6);
                startActivity(n);
            }
        });
    }

    private boolean getVideoList() {
        Cursor cursor = null;
        int count = 0;
        try {
            Uri MEDIA_EXTERNAL_CONTENT_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            String _ID = "_id";
            String MEDIA_DATA = "_data";
            String _NAME = "_display_name";
            String _DURATION = "duration";
            String _DATEVIDEO = "date_added";
            String[] proj = new String[]{"_id", "_data", "_display_name", "duration", "date_added"};
            String[] strArr = new String[1];
            String str = "%";
            strArr[0] = "%" + getResources().getString(R.string.folder_name) +"/" + "Cut" + str;
            cursor = managedQuery(MEDIA_EXTERNAL_CONTENT_URI, proj, "_data like ? ", strArr, "datetaken DESC");
            count = cursor.getCount();
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("2"));
        }
        if (count <= 0) {
            textnot.setVisibility(View.VISIBLE);
            return false;
        }
        cursor.moveToFirst();
        for (int i = 0; i < count; i++) {
            try {
                Uri uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, ContentUtill.getLong(cursor));
                String videoName = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
                String str2 = videoName;
                Uri uri2 = uri;
                String string = cursor.getString(cursor.getColumnIndex("_data"));
                this.videoList.add(new VideoData(str2, uri2, string, ContentUtill.getTime(cursor, "duration")));
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                FirebaseCrash.report(new Exception("3"));
            }
        }
        return true;
    }

    public int dpToPx(int dp) {
        return Math.round(((float) dp) * getApplicationContext().getResources().getDisplayMetrics().density);
    }

    public void deleteTmpFile(int videopath) {
        String s = videoList.get(videopath).videoPath;
        File file = new File(s);
        try {
            if (file.exists()) {
                file.delete();
                getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data =?", new String[]{s});
                Toast.makeText(getApplicationContext(), "\u062d\u0630\u0641 \u0641\u06cc\u0644\u0645 \u0628\u0627 \u0645\u0648\u0641\u0642\u06cc\u062a \u0627\u0646\u062c\u0627\u0645 \u0634\u062f.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("4"));
        }
    }
    public void shareTmpFile(int videopath) {
        String s = videoList.get(videopath).videoPath;
        Intent share = new Intent("android.intent.action.SEND");
        share.setType("video/*");
        share.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(s)));
        startActivity(Intent.createChooser(share, "با چه برنامه ای؟"));
    }
    public void callVideo(int position) {
        Intent intent = new Intent(this, ShareVideoActivity.class);
        intent.putExtra("videofilename", ((VideoData) this.videoList.get(position)).videoPath);
        intent.putExtra("position", position);
        intent.putExtra("isfrommain", false);
        startActivityForResult(intent, BACK_FROM_VIDEOSHARE);
    }

    public void detailFile(int position){
        String name = videoList.get(position).videoName;
        String uri =  videoList.get(position).videoPath;
        File file = new File(uri);
        String Duration = videoList.get(position).Duration;
        String size = null;
        long Filesize = getFolderSize(file)/1024;
        if (Filesize >= 1024){
            size = Filesize/1024 + "MB";
        }else {
            size = Filesize + "KB";
        }
        DialogUtils.showDetailDialog(cut_list.act,name,size,uri,Duration);
    }

    public static long getFolderSize(File f){
        long size = 0;
        if (f.isDirectory()){
            for (File file : f.listFiles()){
                size += getFolderSize(file);
            }
        }else {
            size = f.length();
        }
        return size;
    }

    protected void onPause() {
        this.wl.release();
        super.onPause();
    }

    protected void onResume() {
        this.wl.acquire();
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.imgLoader != null) {
            this.imgLoader.clearDiscCache();
            this.imgLoader.clearMemoryCache();
        }
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCache(new WeakMemoryCache()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(50)).build()).build();
        this.imgLoader = ImageLoader.getInstance();
        this.imgLoader.init(config);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case BACK_FROM_VIDEOSHARE /*99*/:
                    int pos = data.getIntExtra("position", 0);
                    this.videoList.remove(pos);
                    this.adapter.removeItem(pos);
                    break;
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }

}
