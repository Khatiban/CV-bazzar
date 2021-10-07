package com.fury.cv.view;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.fury.cv.R;
import com.fury.cv.model.VideoPlayerState;
import com.fury.cv.util.FileUtils;
import com.fury.cv.util.VideoEngine;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.google.firebase.crash.FirebaseCrash;

public class ViewVideo extends Activity implements OnSeekBarChangeListener, OnClickListener {
    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    Button btnPlay;
    TextView btn_low;
    TextView btn_medium;
    static ProgressDialog dialog;
    int duration;
    Handler handler;
    boolean isPlay;
    public static ViewVideo act;
    int f77k;
    int ch,coinint;
    private NotificationCompat myNotification;
    private NotificationManager notificationManager;
    boolean ok;
    OnClickListener onclickbtnPlay;
    protected static String outputformat;
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
    View videoControlBtn;
    private VideoPlayerState videoPlayerState;
    private StateObserver videoStateObserver;
    VideoView vvScreen;
    private WakeLock wl;


    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    FFmpeg ffmpeg;
    Uri uri;
    private Builder mBuilder;

    /* renamed from: com.video.compressop.view.ViewVideo.1 */
    class C10551 implements Runnable {
        C10551() {
        }

        public void run() {
            if (ViewVideo.this.vvScreen.isPlaying()) {
                int curPos = ViewVideo.this.vvScreen.getCurrentPosition();
                ViewVideo.this.seekVideo.setProgress(curPos);
                try {
                    ViewVideo.this.tvStartVideo.setText(ViewVideo.formatTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == ViewVideo.this.duration) {
                    ViewVideo.this.seekVideo.setProgress(0);
                    ViewVideo.this.btnPlay.setBackgroundResource(R.drawable.play);
                    ViewVideo.this.tvStartVideo.setText("00:00");
                    ViewVideo.this.handler.removeCallbacks(ViewVideo.this.seekrunnable);
                    return;
                }
                ViewVideo.this.handler.postDelayed(ViewVideo.this.seekrunnable, 500);
                return;
            }
            ViewVideo.this.seekVideo.setProgress(ViewVideo.this.duration);
            try {
                ViewVideo.this.tvStartVideo.setText(ViewVideo.formatTimeUnit((long) ViewVideo.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            ViewVideo.this.handler.removeCallbacks(ViewVideo.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.2 */
    class C10562 implements OnClickListener {
        C10562() {
        }

        public void onClick(View v) {
            if (ViewVideo.this.isPlay) {
                ViewVideo.this.vvScreen.pause();
                ViewVideo.this.handler.removeCallbacks(ViewVideo.this.seekrunnable);
                ViewVideo.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                ViewVideo.this.vvScreen.seekTo(ViewVideo.this.seekVideo.getProgress());
                ViewVideo.this.vvScreen.start();
                ViewVideo.this.handler.postDelayed(ViewVideo.this.seekrunnable, 500);
                ViewVideo.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            ViewVideo.this.isPlay = !ViewVideo.this.isPlay;
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.3 */
    class C10573 implements OnErrorListener {
        C10573() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(ViewVideo.this.getApplicationContext(), "Video Player Not Supporting", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.4 */
    class C10584 implements OnPreparedListener {
        C10584() {
        }

        public void onPrepared(MediaPlayer mp) {
            ViewVideo.this.duration = ViewVideo.this.vvScreen.getDuration();
            ViewVideo.this.seekVideo.setMax(ViewVideo.this.duration);
            ViewVideo.this.tvStartVideo.setText("00:00");
            try {
                ViewVideo.this.tvEndVideo.setText(ViewVideo.formatTimeUnit((long) ViewVideo.this.duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("3"));
            }
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.5 */
    class C10595 implements OnCompletionListener {
        C10595() {
        }

        public void onCompletion(MediaPlayer mp) {
            ViewVideo.this.btnPlay.setBackgroundResource(R.drawable.play);
            ViewVideo.this.vvScreen.seekTo(0);
            ViewVideo.this.seekVideo.setProgress(0);
            ViewVideo.this.tvStartVideo.setText("00:00");
            ViewVideo.this.handler.removeCallbacks(ViewVideo.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.6 */
    class C10606 implements OnTouchListener {
        C10606() {
        }

        public boolean onTouch(View arg0, MotionEvent arg1) {
            return false;
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.7 */
    class C10617 implements OnClickListener {
        C10617() {
        }

        public void onClick(View arg0) {
            ViewVideo.this.onBackPressed();
        }
    }

    /* renamed from: com.video.compressop.view.ViewVideo.8 */
    class C10628 implements OnClickListener {
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
                    stopService(new Intent(ViewVideo.this, VideoEngine.class));
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("4"));
                }

                String quality = "";
                if (outputformat != null) {
                    if (outputformat.equalsIgnoreCase("Medium")) {
                        quality = "25";
                    } else if (outputformat.equalsIgnoreCase("Low")) {
                        quality = "30";
                    } else {
                        quality = outputformat;
                    }
                    String inputFileName = videoPlayerState.getFilename();
                    path = FileUtils.getTargetFileName(inputFileName, 1);
                    start_activ();

                    if (coin_alfa){
                        coinint = 100;
                    }
                    if (coinint <= 0){
                        try {
                            end_activ();
                            (new DialogNoTicket(ViewVideo.this)).showDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {


                        one_play_editor.putInt("code", 1);
                        one_play_editor.putInt("one", 1);
                        one_play_editor.putString("inputFileName", inputFileName);
                        one_play_editor.putString("quality", quality);
                        one_play_editor.putString("path", path);
                        one_play_editor.putString("b_S_t", "در حال کاهش حجم فیلم");
                        one_play_editor.putString("b_F_c", "متاسفانه کاهش حجم انجام نشد !");
                        one_play_editor.putString("b_E_t", "کاهش حجم فیلم");
                        one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                        one_play_editor.commit();
                        startService(new Intent(ViewVideo.this, VideoEngine.class));

                    }
            } else {
                Toast.makeText(ViewVideo.this, "error3", Toast.LENGTH_LONG).show();
            }


        }

        else

        {
            Toast.makeText(ViewVideo.this, "please select Quality", Toast.LENGTH_LONG).show();
        }
    }
}

    public void start_activ() {
        dialog = new ProgressDialog(ViewVideo.this);
        dialog.setMessage("\u062f\u0631 \u062d\u0627\u0644 \u0627\u0646\u062c\u0627\u0645 \u06a9\u0627\u0631...");
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void end_activ() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("6"));
        }
    }

    public static void in_activ() {
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

    /* renamed from: com.video.compressop.view.ViewVideo.StateObserver.1 */
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

    public ViewVideo() {
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
        intent.putExtra("activ", 1);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.cut_view);
        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();

        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        //ffmpeg = FFmpeg.getInstance(ViewVideo.this);
        //loadFFMpegBinary();
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
        this.outputformat = "Low";
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
        this.btn_low = (TextView) findViewById(R.id.btn_low);
        this.btn_medium = (TextView) findViewById(R.id.btn_medium);
        this.btn_low.setOnClickListener(this);
        this.btn_medium.setOnClickListener(this);
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String quality;
                if (outputformat.equalsIgnoreCase("Medium")) {
                    quality = "25";
                } else if (outputformat.equalsIgnoreCase("Low")) {
                    quality = "30";
                } else {
                    quality = outputformat;
                }
                DialogUtils.showSettingDialog(ViewVideo.act, new C03981(), quality);
            }
        });

        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        btn_low.setTypeface(face);
        btn_medium.setTypeface(face);
        trimButton.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        TextView text_help = (TextView) findViewById(R.id.text_help);
        TextView ts_check = (TextView) findViewById(R.id.ts_check);
        final ImageView img_check = (ImageView) findViewById(R.id.img_check);
        LinearLayout click_check = (LinearLayout) findViewById(R.id.click_check);
        LinearLayout col_help = (LinearLayout) findViewById(R.id.col_help);
        tvt.setTypeface(face);
        text_help.setTypeface(face);
        ts_check.setTypeface(face);

        int check = one_play_preferences.getInt("check_1", 0);
        if (check == 1) {
            col_help.setVisibility(View.GONE);
        }
        ch = 0;
        click_check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch == 0) {
                    ch = 1;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_on);
                    one_play_editor.putInt("check_1", 1);
                    one_play_editor.apply();
                } else {
                    ch = 0;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_off);
                    one_play_editor.putInt("check_1", 0);
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


    private void loadFFMpegBinary() {
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Toast.makeText(ViewVideo.this, "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
                }
            });
        } catch (FFmpegNotSupportedException e) {
            Toast.makeText(ViewVideo.this, "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
        }
    }


    private OnClickListener trimClickListener() {
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
            seconds = (seconds / 1000) % 60;
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
            case R.id.btn_low /*2131099663*/:
                this.btn_low.setBackgroundResource(R.drawable.btn_on);
                this.btn_medium.setBackgroundResource(R.drawable.btn_off);
                this.outputformat = "Low";
                break;
            case R.id.btn_medium /*2131099664*/:
                this.btn_medium.setBackgroundResource(R.drawable.btn_on);
                this.btn_low.setBackgroundResource(R.drawable.btn_off);
                this.outputformat = "Medium";
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
            FirebaseCrash.report(new Exception("10"));
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
