package com.fury.cv.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.MediaStore;
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
 * Created by fury on 5/17/2017.
 */
public class Voice extends Activity implements SeekBar.OnSeekBarChangeListener{


    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    Button btnPlay;
    static ProgressDialog dialog;
    int duration,coinint;
    Handler handler;
    boolean isPlay;
    public static Voice act;
    int f77k,ch,work;
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
    String MP3Path;
    Typeface face;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    /* renamed from: com.video.compressop.view.Format.1 */
    class C10551 implements Runnable {
        C10551() {
        }

        public void run() {
            if (Voice.this.vvScreen.isPlaying()) {
                int curPos = Voice.this.vvScreen.getCurrentPosition();
                Voice.this.seekVideo.setProgress(curPos);
                try {
                    Voice.this.tvStartVideo.setText(Voice.formatTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == Voice.this.duration) {
                    Voice.this.seekVideo.setProgress(0);
                    Voice.this.btnPlay.setBackgroundResource(R.drawable.play);
                    Voice.this.tvStartVideo.setText("00:00");
                    Voice.this.handler.removeCallbacks(Voice.this.seekrunnable);
                    return;
                }
                Voice.this.handler.postDelayed(Voice.this.seekrunnable, 500);
                return;
            }
            Voice.this.seekVideo.setProgress(Voice.this.duration);
            try {
                Voice.this.tvStartVideo.setText(Voice.formatTimeUnit((long) Voice.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            Voice.this.handler.removeCallbacks(Voice.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Format.2 */
    class C10562 implements View.OnClickListener {
        C10562() {
        }

        public void onClick(View v) {
            if (Voice.this.isPlay) {
                Voice.this.vvScreen.pause();
                Voice.this.handler.removeCallbacks(Voice.this.seekrunnable);
                Voice.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                Voice.this.vvScreen.seekTo(Voice.this.seekVideo.getProgress());
                Voice.this.vvScreen.start();
                Voice.this.handler.postDelayed(Voice.this.seekrunnable, 500);
                Voice.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            Voice.this.isPlay = !Voice.this.isPlay;
        }
    }

    /* renamed from: com.video.compressop.view.Format.3 */
    class C10573 implements MediaPlayer.OnErrorListener {
        C10573() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(Voice.this.getApplicationContext(), "Video Player Not Supporting", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.Format.4 */
    class C10584 implements MediaPlayer.OnPreparedListener {
        C10584() {
        }

        public void onPrepared(MediaPlayer mp) {
            Voice.this.duration = Voice.this.vvScreen.getDuration();
            Voice.this.seekVideo.setMax(Voice.this.duration);
            Voice.this.tvStartVideo.setText("00:00");
            try {
                Voice.this.tvEndVideo.setText(Voice.formatTimeUnit((long) Voice.this.duration));
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
            Voice.this.btnPlay.setBackgroundResource(R.drawable.play);
            Voice.this.vvScreen.seekTo(0);
            Voice.this.seekVideo.setProgress(0);
            Voice.this.tvStartVideo.setText("00:00");
            Voice.this.handler.removeCallbacks(Voice.this.seekrunnable);
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
            Voice.this.onBackPressed();
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
            if (MP3Path != null) {


                try {
                    stopService(new Intent(Voice.this, VideoEngine.class));
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("4"));
                }

                if (MP3Path != null) {

                    String inputFileName = videoPlayerState.getFilename();
                    path = FileUtils.getTargetFileNameVoice(inputFileName,1);

                    try {
                        start_activ();

                        if (coin_alfa){
                            coinint = 100;
                        }
                        if (coinint <= 0){
                            try {
                                end_activ();
                                (new DialogNoTicket(Voice.this)).showDialog();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            one_play_editor.putInt("code", 5);
                            one_play_editor.putInt("one", 1);
                            one_play_editor.putString("inputFileName", inputFileName);
                            one_play_editor.putString("quality", MP3Path);
                            one_play_editor.putString("format_ch", outputsize);
                            one_play_editor.putString("path", path);
                            one_play_editor.putString("b_S_t", "در حال صدا گذاری ویدیو ...");
                            one_play_editor.putString("b_F_c", "متاسفانه صدا گذاری انجام نشد!");
                            one_play_editor.putString("b_E_t", "صدا گذاری");
                            one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                            one_play_editor.commit();

                            work = one_play_preferences.getInt("work", 0);
                            if (work == 4){
                                (new DialogFollow(Voice.this)).showDialog();
                            }else if (work == 5){
                                (new DialogStar(Voice.this)).showDialog();
                            }

                            startService(new Intent(Voice.this, VideoEngine.class));
                        }
                    } catch (Exception e) {
                        FirebaseCrash.report(new Exception("5"));
                        Toast.makeText(Voice.this, "error 2", Toast.LENGTH_LONG).show();
                        File appVoice = new File(Voice.this.path);
                        if (appVoice.exists()) {
                            appVoice.delete();
                            Voice.this.finish();
                        }
                    }
                } else {
                    Toast.makeText(Voice.this, "لطفا صوت مورد نظر خود را انتخاب کنید", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Voice.this, "لطفا صوت مورد نظر خود را انتخاب کنید", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void start_activ(){
        dialog = new ProgressDialog(Voice.this);
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

        /* renamed from: com.video.compressop.view.Format.StateObserver.1 */
        class C10631 implements Runnable {
            C10631() {
            }

            public void run() {
                Voice.StateObserver.this.startVideoProgressObserving();
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

    public Voice() {
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
        MP3Path = null;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(this, SelectVideoActivity.class);
        intent.addFlags(335544320);
        intent.putExtra("activ",5);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.voice_view);

        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("8"));
        }

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("9"));
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
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogHelp(Voice.this,"لطفا ابتدا صوت که می خواهید روی ویدیو قرار دهید را انتخاب کنید و بعد گزینه شروع را کلیک کنید و منتظر قرار دادن صوت بر روی ویدیو شوید \n توجه کنید زمان صوت با ویدیو برابر باشد در غیر اینصورت اگر بیشتر باشد صفحه سیاه پخش میشود و صوت پخش می شود و در صورت کمتر بودن صوت به پایان می رسد و ویدیو همچنان پخش می شود .\n هر گونه سوال و یا مشکلی برایتان پیش آمد از طریق پشتیبانی با ما در ارتباط باشید.")).showDialog();
            }
        });

         face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        trimButton.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        TextView text_help = (TextView) findViewById(R.id.text_help);
        TextView ts_check = (TextView) findViewById(R.id.ts_check);
        TextView select = (TextView) findViewById(R.id.select);
        final ImageView img_check = (ImageView) findViewById(R.id.img_check);
        LinearLayout click_check = (LinearLayout) findViewById(R.id.click_check);
        LinearLayout col_help = (LinearLayout) findViewById(R.id.col_help);
        tvt.setTypeface(face);
        text_help.setTypeface(face);
        ts_check.setTypeface(face);
        select.setTypeface(face);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,1);

                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("audio/mp3"); // specify "audio/mp3" to filter only mp3 files
                //startActivityForResult(intent,1);

            }
        });

        int check = one_play_preferences.getInt("check_5",0);
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
                    one_play_editor.putInt("check_5", 1);
                    one_play_editor.apply();
                }else {
                    ch = 0;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_off);
                    one_play_editor.putInt("check_5", 0);
                    one_play_editor.apply();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Uri audio = data.getData(); //declared above Uri audio;
            MP3Path = String.valueOf(new File(getRealPathFromURI(audio)));
            Log.d("media", "onActivityResult: "+audio);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
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
            FirebaseCrash.report(new Exception("11"));
            return null;
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
            FirebaseCrash.report(new Exception("12"));
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
