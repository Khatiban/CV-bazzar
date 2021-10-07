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
import android.widget.LinearLayout;
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
 * Created by fury on 5/7/2017.
 */
public class Music extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    Button btnPlay;
    TextView f_mp3, f_m4a, f_aac, f_wav, f_aiff, f_wma, f_128  ,f_normal,f_320;
    static ProgressDialog dialog;
    int duration,work;
    Handler handler;
    boolean isPlay;
    public static Music act;
    int f77k,ch,coinint;
    boolean ok;
    View.OnClickListener onclickbtnPlay;
    protected static String outputformat,outputsize;
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
    Typeface face;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    /* renamed from: com.video.compressop.view.Format.1 */
    class C10551 implements Runnable {
        C10551() {
        }

        public void run() {
            if (Music.this.vvScreen.isPlaying()) {
                int curPos = Music.this.vvScreen.getCurrentPosition();
                Music.this.seekVideo.setProgress(curPos);
                try {
                    Music.this.tvStartVideo.setText(Music.formatTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == Music.this.duration) {
                    Music.this.seekVideo.setProgress(0);
                    Music.this.btnPlay.setBackgroundResource(R.drawable.play);
                    Music.this.tvStartVideo.setText("00:00");
                    Music.this.handler.removeCallbacks(Music.this.seekrunnable);
                    return;
                }
                Music.this.handler.postDelayed(Music.this.seekrunnable, 500);
                return;
            }
            Music.this.seekVideo.setProgress(Music.this.duration);
            try {
                Music.this.tvStartVideo.setText(Music.formatTimeUnit((long) Music.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            Music.this.handler.removeCallbacks(Music.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Format.2 */
    class C10562 implements View.OnClickListener {
        C10562() {
        }

        public void onClick(View v) {
            if (Music.this.isPlay) {
                Music.this.vvScreen.pause();
                Music.this.handler.removeCallbacks(Music.this.seekrunnable);
                Music.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                Music.this.vvScreen.seekTo(Music.this.seekVideo.getProgress());
                Music.this.vvScreen.start();
                Music.this.handler.postDelayed(Music.this.seekrunnable, 500);
                Music.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            Music.this.isPlay = !Music.this.isPlay;
        }
    }

    /* renamed from: com.video.compressop.view.Format.3 */
    class C10573 implements MediaPlayer.OnErrorListener {
        C10573() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(Music.this.getApplicationContext(), "Video Player Not Supporting", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.Format.4 */
    class C10584 implements MediaPlayer.OnPreparedListener {
        C10584() {
        }

        public void onPrepared(MediaPlayer mp) {
            Music.this.duration = Music.this.vvScreen.getDuration();
            Music.this.seekVideo.setMax(Music.this.duration);
            Music.this.tvStartVideo.setText("00:00");
            try {
                Music.this.tvEndVideo.setText(Music.formatTimeUnit((long) Music.this.duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("3"));
            }
        }
    }

    /* renamed from: com.video.compressop.view.Format.5 */
    class C10595 implements MediaPlayer.OnCompletionListener {
        C10595() {
        }

        public void onCompletion(MediaPlayer mp) {
            Music.this.btnPlay.setBackgroundResource(R.drawable.play);
            Music.this.vvScreen.seekTo(0);
            Music.this.seekVideo.setProgress(0);
            Music.this.tvStartVideo.setText("00:00");
            Music.this.handler.removeCallbacks(Music.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Format.6 */
    class C10606 implements View.OnTouchListener {
        C10606() {
        }

        public boolean onTouch(View arg0, MotionEvent arg1) {
            return false;
        }
    }

    /* renamed from: com.video.compressop.view.Format.7 */
    class C10617 implements View.OnClickListener {
        C10617() {
        }

        public void onClick(View arg0) {
            Music.this.onBackPressed();
        }
    }

    /* renamed from: com.video.compressop.view.Format.8 */
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
            if (outputformat != null) {


                try {
                    stopService(new Intent(Music.this, VideoEngine.class));
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("4"));
                }

                if (outputformat != null) {

                    String inputFileName = videoPlayerState.getFilename();
                    path = FileUtils.getTargetFileNameMusic(inputFileName,1,"." + outputformat);

                    try {
                        start_activ();

                        if (coin_alfa){
                            coinint = 100;
                        }
                        if (coinint <= 0){
                            try {
                                end_activ();
                                (new DialogNoTicket(Music.this)).showDialog();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            one_play_editor.putInt("code", 4);
                            one_play_editor.putInt("one", 1);
                            one_play_editor.putString("inputFileName", inputFileName);
                            one_play_editor.putString("quality", outputformat);
                            one_play_editor.putString("format_ch", outputsize);
                            one_play_editor.putString("path", path);
                            one_play_editor.putString("b_S_t", "در حال جدا سازی صوت از ویدیو");
                            one_play_editor.putString("b_F_c", "متاسفانه صوت جدا نشد !");
                            one_play_editor.putString("b_E_t", "جدا کردن صوت");
                            one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                            one_play_editor.commit();

                            work = one_play_preferences.getInt("work", 0);
                            if (work == 4){
                                (new DialogFollow(Music.this)).showDialog();
                            }else if (work == 5){
                                (new DialogStar(Music.this)).showDialog();
                            }

                            startService(new Intent(Music.this, VideoEngine.class));
                        }
                    } catch (Exception e) {
                        FirebaseCrash.report(new Exception("5"));
                        Toast.makeText(Music.this, "error 2", Toast.LENGTH_LONG).show();
                        File appmusic = new File(Music.this.path);
                        if (appmusic.exists()) {
                            appmusic.delete();
                            Music.this.finish();
                        }
                    }
                } else {
                    Toast.makeText(Music.this, "لطفا یک فرمت مشخص کنید !", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Music.this, "لطفا یک فرمت مشخص کنید !", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void start_activ(){
        dialog = new ProgressDialog(Music.this);
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
        outputformat = null;
    }

    private class StateObserver extends Handler {
        private boolean alreadyStarted;
        private Runnable observerWork;

        /* renamed from: com.video.compressop.view.Format.StateObserver.1 */
        class C10631 implements Runnable {
            C10631() {
            }

            public void run() {
                Music.StateObserver.this.startVideoProgressObserving();
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

    public Music() {
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
        intent.putExtra("activ",4);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.music_view);
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

        findById();
        outputformat = "mp3";
        outputsize = "192";
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
        this.f_mp3 = (TextView) findViewById(R.id.f_mp3);
        this.f_m4a = (TextView) findViewById(R.id.f_m4a);
        this.f_aac = (TextView) findViewById(R.id.f_aac);
        this.f_wav = (TextView) findViewById(R.id.f_wav);
        this.f_aiff = (TextView) findViewById(R.id.f_aiff);
        this.f_wma = (TextView) findViewById(R.id.f_wma);
        this.f_128 = (TextView) findViewById(R.id.f_128);
        this.f_normal = (TextView) findViewById(R.id.f_normal);
        this.f_320 = (TextView) findViewById(R.id.f_320);
        this.f_mp3 .setOnClickListener(this);
        this.f_m4a .setOnClickListener(this);
        this.f_aac .setOnClickListener(this);
        this.f_wav .setOnClickListener(this);
        this.f_aiff .setOnClickListener(this);
        this.f_wma .setOnClickListener(this);
        this.f_128 .setOnClickListener(this);
        this.f_normal .setOnClickListener(this);
        this.f_320 .setOnClickListener(this);
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogHelp(Music.this,"ابتدا فرمت مورد نظر خود را مشخص کنید (بهترین فرمت ها mp3 , m4a می باشد) و بعد کیفیت صدا را انتخاب کنید (استاندارد صدای اصلی ویدیو می باشد) و حالا شروع را کلیک کنید و منتظر جدا کردن صوت از ویدیو شوید \n هر گونه سوال و یا مشکلی برایتان بوجود آمد از طریق پشتیبانی با ما در ارتباط باشید .")).showDialog();
            }
        });

         face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        trimButton.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        TextView txt_co = (TextView) findViewById(R.id.txt_co);
        TextView txt_format = (TextView) findViewById(R.id.txt_format);
        TextView text_help = (TextView) findViewById(R.id.text_help);
        TextView ts_check = (TextView) findViewById(R.id.ts_check);
        final ImageView img_check = (ImageView) findViewById(R.id.img_check);
        LinearLayout click_check = (LinearLayout) findViewById(R.id.click_check);
        LinearLayout col_help = (LinearLayout) findViewById(R.id.col_help);
        tvt.setTypeface(face);
        text_help.setTypeface(face);
        f_normal.setTypeface(face);
        txt_co.setTypeface(face);
        txt_format.setTypeface(face);
        ts_check.setTypeface(face);

        int check = one_play_preferences.getInt("check_4",0);
        if (check == 1){
            col_help.setVisibility(View.GONE);
        }
        ch = 0;
        click_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch == 0){
                    ch = 1;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_on);
                    one_play_editor.putInt("check_4", 1);
                    one_play_editor.apply();
                }else {
                    ch = 0;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_off);
                    one_play_editor.putInt("check_4", 0);
                    one_play_editor.apply();
                }
            }
        });
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

    public static String formatTimeUnit(long millis) throws ParseException {
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

            case R.id.f_mp3 /*2131099663*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_on);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_off);
                    this.f_aac .setBackgroundResource(R.drawable.btn_off);
                    this.f_wav .setBackgroundResource(R.drawable.btn_off);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_off);
                    this.f_wma .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("10"));
                }

                outputformat = "mp3";

                break;
            case R.id.f_m4a /*2131099664*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_off);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_on);
                    this.f_aac .setBackgroundResource(R.drawable.btn_off);
                    this.f_wav .setBackgroundResource(R.drawable.btn_off);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_off);
                    this.f_wma .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("11"));
                }

                outputformat = "m4a";
                break;
            case R.id.f_aac /*2131099664*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_off);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_off);
                    this.f_aac .setBackgroundResource(R.drawable.btn_on);
                    this.f_wav .setBackgroundResource(R.drawable.btn_off);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_off);
                    this.f_wma .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("12"));
                }

                outputformat = "aac";

                break;
            case R.id.f_wav /*2131099664*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_off);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_off);
                    this.f_aac .setBackgroundResource(R.drawable.btn_off);
                    this.f_wav .setBackgroundResource(R.drawable.btn_on);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_off);
                    this.f_wma .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("13"));
                }

                outputformat = "wav";

                break;
            case R.id.f_aiff /*2131099664*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_off);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_off);
                    this.f_aac .setBackgroundResource(R.drawable.btn_off);
                    this.f_wav .setBackgroundResource(R.drawable.btn_off);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_on);
                    this.f_wma .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("14"));
                }

                outputformat = "aiff";

                break;
            case R.id.f_wma /*2131099664*/:

                try {
                    this.f_mp3 .setBackgroundResource(R.drawable.btn_off);
                    this.f_m4a .setBackgroundResource(R.drawable.btn_off);
                    this.f_aac .setBackgroundResource(R.drawable.btn_off);
                    this.f_wav .setBackgroundResource(R.drawable.btn_off);
                    this.f_aiff.setBackgroundResource(R.drawable.btn_off);
                    this.f_wma .setBackgroundResource(R.drawable.btn_on);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("15"));
                }

                outputformat = "wma";

                break;
            case R.id.f_128 /*2131099664*/:

                try {
                    this.f_128  .setBackgroundResource(R.drawable.btn_on);
                    this.f_normal.setBackgroundResource(R.drawable.btn_off);
                    this.f_320 .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("16"));
                }

                outputsize = "128";

                break;
            case R.id.f_normal /*2131099664*/:

                try {
                    this.f_128  .setBackgroundResource(R.drawable.btn_off);
                    this.f_normal.setBackgroundResource(R.drawable.btn_on);
                    this.f_320 .setBackgroundResource(R.drawable.btn_off);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("17"));
                }

                outputsize = "192";

                break;
            case R.id.f_320 /*2131099664*/:

                try {
                    this.f_128  .setBackgroundResource(R.drawable.btn_off);
                    this.f_normal.setBackgroundResource(R.drawable.btn_off);
                    this.f_320 .setBackgroundResource(R.drawable.btn_on);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("18"));
                }

                outputsize = "320";

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
            this.tvStartVideo.setText(formatTimeUnit((long) progress));
        } catch (ParseException e) {
            FirebaseCrash.report(new Exception("19"));
        }
    }

    class C03981 implements DialogUtils.DialogBtnClickListener_set {

        C03981() {
        }

        public void onPositiveClick(String s) {
            outputformat = s;
        }
    }

}
