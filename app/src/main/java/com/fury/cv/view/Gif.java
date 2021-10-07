package com.fury.cv.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.fury.cv.R;
import com.fury.cv.model.VideoPlayerState;
import com.fury.cv.util.FileUtils;
import com.fury.cv.util.VideoEngine;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;

/**
 * Created by fury on 5/9/2017.
 */
public class Gif extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    Button btnPlay;
    TextView f_1,f_2;
    static ProgressDialog dialog;
    int duration;
    Handler handler;
    boolean isPlay;
    public static Gif act;
    int f77k,coinint,work;
    int outputGif,ch,dvd,vcd,svcd,libx264,dv,n,pn,form;
    Typeface face;
    boolean ok;
    View.OnClickListener onclickbtnPlay;
    protected static String sendcode;
    String path;
    Boolean plypush,coin_alfa;
    private PowerManager pm;
    RelativeLayout rl_videoplayer;
    SeekBar seekVideo;
    Runnable seekrunnable;
    TextView trimButton;
    TextView tvEndVideo;
    TextView eroor;
    TextView tvStartVideo;
    private VideoPlayerState videoPlayerState;
    private StateObserver videoStateObserver;
    VideoView vvScreen;
    private PowerManager.WakeLock wl;


    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    /* renamed from: com.video.compressop.view.Gif.1 */
    class C10551 implements Runnable {
        C10551() {
        }

        public void run() {
            if (Gif.this.vvScreen.isPlaying()) {
                int curPos = Gif.this.vvScreen.getCurrentPosition();
                Gif.this.seekVideo.setProgress(curPos);
                try {
                    Gif.this.tvStartVideo.setText(Gif.GifTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == Gif.this.duration) {
                    Gif.this.seekVideo.setProgress(0);
                    Gif.this.btnPlay.setBackgroundResource(R.drawable.play);
                    Gif.this.tvStartVideo.setText("00:00");
                    Gif.this.handler.removeCallbacks(Gif.this.seekrunnable);
                    return;
                }
                Gif.this.handler.postDelayed(Gif.this.seekrunnable, 500);
                return;
            }
            Gif.this.seekVideo.setProgress(Gif.this.duration);
            try {
                Gif.this.tvStartVideo.setText(Gif.GifTimeUnit((long) Gif.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            Gif.this.handler.removeCallbacks(Gif.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Gif.2 */
    class C10562 implements View.OnClickListener {
        C10562() {
        }

        public void onClick(View v) {
            if (Gif.this.isPlay) {
                Gif.this.vvScreen.pause();
                Gif.this.handler.removeCallbacks(Gif.this.seekrunnable);
                Gif.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                Gif.this.vvScreen.seekTo(Gif.this.seekVideo.getProgress());
                Gif.this.vvScreen.start();
                Gif.this.handler.postDelayed(Gif.this.seekrunnable, 500);
                Gif.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            Gif.this.isPlay = !Gif.this.isPlay;
        }
    }

    /* renamed from: com.video.compressop.view.Gif.3 */
    class C10573 implements MediaPlayer.OnErrorListener {
        C10573() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(Gif.this.getApplicationContext(), "Video Player Not Supporting", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.Gif.4 */
    class C10584 implements MediaPlayer.OnPreparedListener {
        C10584() {
        }

        public void onPrepared(MediaPlayer mp) {
            Gif.this.duration = Gif.this.vvScreen.getDuration();
            Gif.this.seekVideo.setMax(Gif.this.duration);
            Gif.this.tvStartVideo.setText("00:00");
            try {
                Gif.this.tvEndVideo.setText(Gif.GifTimeUnit((long) Gif.this.duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("3"));
            }
        }
    }

    /* renamed from: com.video.compressop.view.Gif.5 */
    class C10595 implements MediaPlayer.OnCompletionListener {
        C10595() {
        }

        public void onCompletion(MediaPlayer mp) {
            Gif.this.btnPlay.setBackgroundResource(R.drawable.play);
            Gif.this.vvScreen.seekTo(0);
            Gif.this.seekVideo.setProgress(0);
            Gif.this.tvStartVideo.setText("00:00");
            Gif.this.handler.removeCallbacks(Gif.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Gif.6 */
    class C10606 implements View.OnTouchListener {
        C10606() {
        }

        public boolean onTouch(View arg0, MotionEvent arg1) {
            return false;
        }
    }

    /* renamed from: com.video.compressop.view.Gif.7 */
    class C10617 implements View.OnClickListener {
        C10617() {
        }

        public void onClick(View arg0) {
            Gif.this.onBackPressed();
        }
    }

    /* renamed from: com.video.compressop.view.Gif.8 */
    class C10628 implements View.OnClickListener {
        C10628() {
        }

        public void onClick(View arg0) {
            if (vvScreen.isPlaying()) {
                vvScreen.pause();
                handler.removeCallbacks(seekrunnable);
                btnPlay.setBackgroundResource(R.drawable.play);
                isPlay = false;
            }

                try {
                    stopService(new Intent(Gif.this, VideoEngine.class));
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("4"));
                }

                    String inputFileName = videoPlayerState.getFilename();
                    path = FileUtils.getTargetFileNameGif(inputFileName,1);

                    try {
                        start_activ();

                        if (coin_alfa){
                            coinint = 100;
                        }
                        if (coinint <= 0){
                            try {
                                end_activ();
                                (new DialogNoTicket(Gif.this)).showDialog();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {

                            one_play_editor.putInt("code", 3);
                            one_play_editor.putInt("one", 1);
                            one_play_editor.putInt("gif", outputGif);
                            one_play_editor.putString("inputFileName", inputFileName);
                            one_play_editor.putString("path", path);
                            one_play_editor.putString("b_S_t", "در حال تبدیل به گیف");
                            one_play_editor.putString("b_F_c", "متاسفانه تبدیل به گیف موفق نبود !");
                            one_play_editor.putString("b_E_t", "تبدیل به گیف");
                            one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                            one_play_editor.commit();

                            work = one_play_preferences.getInt("work", 0);
                            if (work == 4){
                                (new DialogFollow(Gif.this)).showDialog();
                            }else if (work == 5){
                                (new DialogStar(Gif.this)).showDialog();
                            }

                            startService(new Intent(Gif.this, VideoEngine.class));
                        }
                    } catch (Exception e) {
                        FirebaseCrash.report(new Exception("5"));
                        Toast.makeText(Gif.this, "error 2", Toast.LENGTH_LONG).show();
                        File appmusic = new File(Gif.this.path);
                        if (appmusic.exists()) {
                            appmusic.delete();
                            Gif.this.finish();
                        }
                    }

        }
    }

    public void start_activ(){
        dialog = new ProgressDialog(Gif.this);
        dialog.setMessage("\u062f\u0631 \u062d\u0627\u0644 \u0627\u0646\u062c\u0627\u0645 \u06a9\u0627\u0631...");
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void end_activ(){
        try {
            dialog.dismiss();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("6"));
        }
    }

    public static void in_activ(){
        try {
            dialog.dismiss();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("7"));
        }
    }

    private class StateObserver extends Handler {
        private boolean alreadyStarted;
        private Runnable observerWork;

        /* renamed from: com.video.compressop.view.Gif.StateObserver.1 */
        class C10631 implements Runnable {
            C10631() {
            }

            public void run() {
                StateObserver.this.startVideoProgressObserving();
            }
        }

        private StateObserver() {
            this.alreadyStarted = false;
            this.observerWork = new C10631();
        }

        private void startVideoProgressObserving() {
            if (!this.alreadyStarted) {
                this.alreadyStarted = true;
                sendEmptyMessage(0);
            }
        }
    }

    public Gif() {
        this.LOADING_DIALOG = MY_NOTIFICATION_ID;
        this.ok = false;
        this.f77k = 0;
        this.videoPlayerState = new VideoPlayerState();
        this.path = null;
        this.duration = 0;
        this.isPlay = false;
        this.handler = new Handler();
        this.plypush = Boolean.valueOf(false);
        this.videoStateObserver = new StateObserver();
        this.seekrunnable = new C10551();
        this.onclickbtnPlay = new C10562();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(this, SelectVideoActivity.class);
        intent.addFlags(335544320);
        intent.putExtra("activ",3);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.gif_view);
        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("8"));
        }
        Object lastState = getLastNonConfigurationInstance();
        if (lastState != null) {
            this.videoPlayerState = (VideoPlayerState) lastState;
        } else {
            Bundle extras = getIntent().getExtras();
            this.videoPlayerState.setFilename(extras.getString("videofilename"));
            this.path = extras.getString("videofilename");
        }

        dvd = 1;
        vcd= 1;
        svcd= 1;
        libx264= 1;
        dv= 1;
        n = 0;
        findById();
        outputGif = 1;
        this.vvScreen.setVideoPath(this.path);
        this.vvScreen.seekTo(100);
        this.vvScreen.setOnErrorListener(new C10573());
        this.vvScreen.setOnPreparedListener(new C10584());
        this.vvScreen.setOnCompletionListener(new C10595());
        this.vvScreen.setOnTouchListener(new C10606());

        FirebaseCrash.log("log 1");
    }

    private void findById() {
        this.rl_videoplayer = (RelativeLayout) findViewById(R.id.rl_videoplayer);
        this.trimButton = (TextView) findViewById(R.id.trimbut);
        this.trimButton.setOnClickListener(trimClickListener());
        this.vvScreen = (VideoView) findViewById(R.id.videoView1);
        this.btnPlay = (Button) findViewById(R.id.buttonply);
        this.btnPlay.setOnClickListener(this.onclickbtnPlay);
        this.rl_videoplayer.setOnClickListener(this.onclickbtnPlay);
        this.tvStartVideo = (TextView) findViewById(R.id.left_pointer);
        this.tvEndVideo = (TextView) findViewById(R.id.right_pointer);
        this.eroor = (TextView) findViewById(R.id.error);
        this.seekVideo = (SeekBar) findViewById(R.id.sbVideo);
        this.seekVideo.setOnSeekBarChangeListener(this);
        this.f_1 = (TextView) findViewById(R.id.f_1);
        this.f_2 = (TextView) findViewById(R.id.f_2);
        this.f_1 .setOnClickListener(this);
        this.f_2 .setOnClickListener(this);
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogHelp(Gif.this,"کم حجم : کمی کیفیت پایین می آید اما حجم زیادی کم میشه \n عادی : کیفیت اصلی اما حجم بالا \n توجه کنید که تبدیل به گیف برای ویدیو های زیر 1 دقیقه مناسب می باشد چون حجم بسیار افزایش پیدا میکند و ممکن است زمان بر باشد \n در صورت که هر گونه سوال و یا مشکلی داشتید از طریق پشتیبانی با ما در ارتباط باشید .")).showDialog();
            }
        });

       face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        trimButton.setTypeface(face);
        f_1.setTypeface(face);
        f_2.setTypeface(face);
    }

    public Object onRetainNonConfigurationInstance() {
        Log.i("VideoView", "In on retain");
        return this.videoPlayerState;
    }

    protected void onResume() {
        super.onResume();
        this.wl.acquire();
        Log.i("VideoView", "In on resume");
    }

    protected void onPause() {
        this.wl.release();
        super.onPause();
        Log.i("VideoView", "In on pause");
    }


    private View.OnClickListener trimClickListener() {
        return new C10628();
    }

    public static String GifTimeUnit(long millis) throws ParseException {
        try {
            String out;
            long hours = millis / 3600000;
            long remaining_minutes = (millis - (3600000 * hours)) / 60000;
            String minutes = String.valueOf(remaining_minutes);
            if (minutes.equals(Integer.valueOf(0))) {
                minutes = "00";
            }
            long seconds = (millis - (3600000 * hours)) - (60000 * remaining_minutes);
            seconds = (seconds / 1000) % 60 ;
            String seconds2 = String.valueOf(seconds);
            if (seconds2.length() < 2) {
                seconds2 = seconds2.substring(0, 1);
            } else {
                seconds2 = seconds2.substring(0, 2);
            }
            if (hours > 0) {
                out = new StringBuilder(String.valueOf(hours)).append(":").append(minutes).append(":").append(seconds2).toString();
            } else {
                out = new StringBuilder(String.valueOf(minutes)).append(":").append(seconds2).toString();
            }
            return out;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("9"));
            return null;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.f_1 /*2131099663*/:

                f_1.setBackgroundResource(R.drawable.btn_on);
                f_2.setBackgroundResource(R.drawable.btn_off);

                outputGif = 1;

                break;
            case R.id.f_2 /*2131099664*/:

                f_1.setBackgroundResource(R.drawable.btn_off);
                f_2.setBackgroundResource(R.drawable.btn_on);

                outputGif = 2;

                break;

        }
    }

    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
    }

    public void onStartTrackingTouch(SeekBar arg0) {
    }

    public void onStopTrackingTouch(SeekBar seekbar) {
        int progress = seekbar.getProgress();
        this.vvScreen.seekTo(progress);
        try {
            this.tvStartVideo.setText(GifTimeUnit((long) progress));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    class C03981 implements DialogUtils.DialogBtnClickListener_set {

        C03981() {
        }

        public void onPositiveClick(String s) {

        }
    }

}
