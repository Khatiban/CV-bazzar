package com.fury.cv.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap.Config;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.MediaStore.Video.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fury.cv.R;
import com.fury.cv.adapter.SelectVideoAdapter;
import com.fury.cv.model.VideoData;
import com.fury.cv.util.ContentUtill;
import com.google.firebase.crash.FirebaseCrash;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.Locale;

public class SelectVideoActivity extends Activity {
    ImageView btnBack;
    ImageLoader imgLoader;
    private PowerManager pm;
    ArrayList<VideoData> videoList;
    Cursor videocursor;
    ListView videogrid;
    private WakeLock wl;
    EditText serach;
    SelectVideoAdapter adapter;
    int tedad;
    int ac;

    /* renamed from: com.video.compressop.view.SelectVideoActivity.1 */
    class C10441 implements OnClickListener {
        C10441() {
        }

        public void onClick(View arg0) {
            SelectVideoActivity.this.onBackPressed();
        }
    }

    @SuppressLint({"NewApi"})
    private class loadVideo extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog pd;

        private loadVideo() {
            this.pd = null;
        }

        protected void onPreExecute() {
            this.pd = new ProgressDialog(SelectVideoActivity.this);
            this.pd.setMessage("\u0635\u0628\u0631 \u06a9\u0646\u06cc\u062f...");
            this.pd.setCancelable(false);
            this.pd.show();
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(SelectVideoActivity.this.getVideoList());
        }

        protected void onPostExecute(Boolean result) {
            this.pd.dismiss();
            if (result.booleanValue()) {
                adapter = new SelectVideoAdapter(SelectVideoActivity.this, videoList, imgLoader);
                videogrid.setAdapter(adapter);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private class loadVideo_filter extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog pd;
        String c;  //car = car.toLowerCase(Locale.getDefault());
        private loadVideo_filter(String v) {
            this.pd = null;
            c = v;
            c = c.toLowerCase(Locale.getDefault());
        }

        protected void onPreExecute() {
            this.pd = new ProgressDialog(SelectVideoActivity.this);
            this.pd.setMessage("\u0635\u0628\u0631 \u06a9\u0646\u06cc\u062f...");
            this.pd.setCancelable(false);
            this.pd.show();
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(SelectVideoActivity.this.getVideoList_filter(c));
        }

        protected void onPostExecute(Boolean result) {
            this.pd.dismiss();
            if (result.booleanValue()) {
                adapter = new SelectVideoAdapter(SelectVideoActivity.this, videoList, imgLoader);
                videogrid.setAdapter(adapter);
            }
        }
    }

    public SelectVideoActivity() {
        this.videoList = new ArrayList();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.imgLoader != null) {
            this.imgLoader.clearDiscCache();
            this.imgLoader.clearMemoryCache();
        }
    }

    public void onBackPressed() {
        finish();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.main);
        serach = (EditText) findViewById(R.id.serach_item);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(6, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("1"));
        }
        initImageLoader();
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnBack.setOnClickListener(new C10441());
        this.videogrid = (ListView) findViewById(R.id.VideogridView);
        new loadVideo().execute();

        try {
            Bundle extras = getIntent().getExtras();
            ac = extras.getInt("activ");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("2"));
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        serach.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        tvt.setTypeface(face);


        serach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = serach.getText().toString().toLowerCase(Locale.getDefault());
                new loadVideo_filter(text).execute();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        FirebaseCrash.log("log 1");

    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCache(new WeakMemoryCache()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(400)).build()).build();
        this.imgLoader = ImageLoader.getInstance();
        this.imgLoader.init(config);
    }

    protected void onResume() {
        this.wl.acquire();
        super.onResume();
    }

    protected void onPause() {
        this.wl.release();
        super.onPause();
    }

    @SuppressLint({"NewApi"})
    private boolean getVideoList() {
        String _ID = "_id";
        String MEDIA_DATA = "_data";
        String _NAME = "_display_name";
        String _DURATION = "duration";
        Cursor cursor = managedQuery(Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "_display_name", "duration"}, null, null, " _id DESC");
        int count = cursor.getCount();
        tedad = count;
        if (count <= 0) {
            return false;
        }
        cursor.moveToFirst();
        for (int i = 0; i < count; i++) {
            Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, ContentUtill.getLong(cursor));
            String videoName = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
            String str = videoName;
            Uri uri2 = uri;
            String string = cursor.getString(cursor.getColumnIndex("_data"));
            this.videoList.add(new VideoData(str, uri2, string, ContentUtill.getTime(cursor, "duration")));
            cursor.moveToNext();
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private boolean getVideoList_filter(String car) {
        videoList.clear();
        String _ID = "_id";
        String MEDIA_DATA = "_data";
        String _NAME = "_display_name";
        String _DURATION = "duration";
        Cursor cursor = managedQuery(Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "_display_name", "duration"}, null, null, " _id DESC");
        int count = cursor.getCount();
        tedad = count;
        if (count <= 0) {
            return false;
        }
        cursor.moveToFirst();
        if (car.length() == 0){
            for (int i = 0; i < count; i++) {
                Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, ContentUtill.getLong(cursor));
                String videoName = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
                String str = videoName;
                Uri uri2 = uri;
                String string = cursor.getString(cursor.getColumnIndex("_data"));
                this.videoList.add(new VideoData(str, uri2, string, ContentUtill.getTime(cursor, "duration")));
                cursor.moveToNext();
            }
            return true;
        }
        for (int i = 0; i < count; i++) {
            Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, ContentUtill.getLong(cursor));
            String videoName = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
            if (videoName.contains(car)){
                String str = videoName;
                Uri uri2 = uri;
                String string = cursor.getString(cursor.getColumnIndex("_data"));
                videoList.add(new VideoData(videoName, uri2, string, ContentUtill.getTime(cursor, "duration")));
            }
            cursor.moveToNext();

        }
        return true;
    }


    public void callVideo(int position) {
        if (ac == 1){
            Intent intent = new Intent(this, ViewVideo.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 2){
            Intent intent = new Intent(this, Format.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 3){
            Intent intent = new Intent(this, Gif.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 4){
            Intent intent = new Intent(this, Music.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 5){
            Intent intent = new Intent(this, Voice.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 6){
            Intent intent = new Intent(this, Cut.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }else if (ac == 7){
            Intent intent = new Intent(this, Logo.class);
            intent.putExtra("videofilename", ((VideoData) videoList.get(position)).videoPath);
            intent.putExtra("position", position);
            intent.putExtra("isfrommain", false);
            intent.addFlags(335544320);
            startActivity(intent);
        }
        finish();
    }

    protected void onStop() {
        super.onStop();
    }
}
