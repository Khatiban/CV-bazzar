package com.fury.cv.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
public class Logo extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {


    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    Button btnPlay;
    static ProgressDialog dialog;
    int duration;
    Handler handler;
    boolean isPlay,coin_alfa;
    public static Logo act;
    int ch,in;
    int f77k, start, end;
    boolean ok;
    Typeface face;
    View.OnClickListener onclickbtnPlay;
    protected static String outputformat, outputsize;
    String path;
    Boolean plypush;
    private PowerManager pm;
    RelativeLayout rl_videoplayer;
    SeekBar seekVideo, seek_in, seek_out;
    Runnable seekrunnable;
    TextView trimButton;
    TextView tvEndVideo;
    TextView eroor;
    EditText hEdit, secEdit, minEdit, hEdit2, secEdit2, minEdit2;
    TextView tvStartVideo;
    private VideoPlayerState videoPlayerState;
    private StateObserver videoStateObserver;
    VideoView vvScreen;
    private PowerManager.WakeLock wl;
    String MP3Path;
    int st,d,coinint,work;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    /* renamed from: com.video.compressop.view.Format.1 */
    class C10551 implements Runnable {
        C10551() {
        }

        public void run() {
            if (Logo.this.vvScreen.isPlaying()) {
                int curPos = Logo.this.vvScreen.getCurrentPosition();
                Logo.this.seekVideo.setProgress(curPos);
                try {
                    Logo.this.tvStartVideo.setText(Logo.formatTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == Logo.this.duration) {
                    Logo.this.seekVideo.setProgress(0);
                    Logo.this.btnPlay.setBackgroundResource(R.drawable.play);
                    Logo.this.tvStartVideo.setText("00:00");
                    Logo.this.handler.removeCallbacks(Logo.this.seekrunnable);
                    return;
                }
                Logo.this.handler.postDelayed(Logo.this.seekrunnable, 500);
                return;
            }
            Logo.this.seekVideo.setProgress(Logo.this.duration);
            try {
                Logo.this.tvStartVideo.setText(Logo.formatTimeUnit((long) Logo.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            Logo.this.handler.removeCallbacks(Logo.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.Format.2 */
    class C10562 implements View.OnClickListener {
        C10562() {
        }

        public void onClick(View v) {
            if (Logo.this.isPlay) {
                Logo.this.vvScreen.pause();
                Logo.this.handler.removeCallbacks(Logo.this.seekrunnable);
                Logo.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                Logo.this.vvScreen.seekTo(Logo.this.seekVideo.getProgress());
                Logo.this.vvScreen.start();
                Logo.this.handler.postDelayed(Logo.this.seekrunnable, 500);
                Logo.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            Logo.this.isPlay = !Logo.this.isPlay;
        }
    }

    /* renamed from: com.video.compressop.view.Format.3 */
    class C10573 implements MediaPlayer.OnErrorListener {
        C10573() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            FirebaseCrash.report(new Exception("3"));
            Toast.makeText(Logo.this.getApplicationContext(), "Video Player Not Supporting", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.Format.4 */
    class C10584 implements MediaPlayer.OnPreparedListener {
        C10584() {
        }

        public void onPrepared(MediaPlayer mp) {
            Logo.this.duration = Logo.this.vvScreen.getDuration();
            Logo.this.seekVideo.setMax(Logo.this.duration);
            Logo.this.seek_in.setMax(Logo.this.duration);
            Logo.this.seek_out.setMax(Logo.this.duration);
            Logo.this.seek_out.setProgress(duration);
            try {
                hEdit2.setText(formatTimeUnit_ho((long) duration));
                minEdit2.setText(formatTimeUnit_min((long) duration));
                secEdit2.setText(formatTimeUnit_sec((long) duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("4"));
            }
            Logo.this.tvStartVideo.setText("00:00");
            try {
                Logo.this.tvEndVideo.setText(Logo.formatTimeUnit((long) Logo.this.duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("5"));
            }
        }
    }

    /* renamed from: com.video.compressop.view.Format.5 */
    class C10595 implements MediaPlayer.OnCompletionListener {
        C10595() {
        }

        public void onCompletion(MediaPlayer mp) {
            Logo.this.btnPlay.setBackgroundResource(R.drawable.play);
            Logo.this.vvScreen.seekTo(0);
            Logo.this.seekVideo.setProgress(0);
            Logo.this.tvStartVideo.setText("00:00");
            Logo.this.handler.removeCallbacks(Logo.this.seekrunnable);
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
            Logo.this.onBackPressed();
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

            try {
                stopService(new Intent(Logo.this, VideoEngine.class));
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("6"));
            }
            in = 0;
            if (MP3Path != null){

                if (st == 13){
                    //top right
                    d = 1;
                }else if (st == 11){
                    //top left
                    d = 2;
                }else if (st == 3){
                    //center
                    d = 3;
                }else if (st == 12){
                    //bottom left
                    d = 4;
                }else if (st == 14){
                    //bottom right
                    d = 5;
                }else if (st == 19){
                    //left to right
                    d = 6;
                }else {
                    //11
                    //top left
                    d = 2;

                }

                if (ch == 1){
                    if (start < end) {

                        String hor_in = hEdit.getText().toString();
                        String min_in = minEdit.getText().toString();
                        String sec_in = secEdit.getText().toString();

                        String hor_out = hEdit2.getText().toString();
                        String min_out = minEdit2.getText().toString();
                        String sec_out = secEdit2.getText().toString();

                        int h = formatTimeUnit_ho_back(hor_in);
                        int m = formatTimeUnit_min_back(min_in);
                        int s = formatTimeUnit_sec_back(sec_in);

                        int h2 = formatTimeUnit_ho_back(hor_out);
                        int m2 = formatTimeUnit_min_back(min_out);
                        int s2 = formatTimeUnit_sec_back(sec_out);

                        int all_in = h + m + s;
                        int all_out = h2 + m2 + s2;

                        if (all_in < all_out) {
                            if (all_in < duration) {
                                if (all_out < duration) {
                                    int timeReal = all_out - all_in;
                                    String timeStartCut = formatTimeUnit((long) all_in);
                                    String timeEndCut = formatTimeUnit((long) timeReal);
                                    in = 1;
                                    try {
                                        one_play_editor.putString("quality", String.valueOf(all_in));
                                        one_play_editor.putString("format_ch", String.valueOf(timeReal));
                                        one_play_editor.commit();

                                    } catch (Exception e) {
                                        FirebaseCrash.report(new Exception("7"));
                                        Toast.makeText(Logo.this, "error 2", Toast.LENGTH_LONG).show();
                                        File appVoice = new File(Logo.this.path);
                                        if (appVoice.exists()) {
                                            appVoice.delete();
                                            Logo.this.finish();
                                        }
                                    }

                                } else {
                                    Toast.makeText(Logo.this, "زمان پایان برش از زمان کل ویدیو بیشتر است !", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Logo.this, "زمان شروع برش از زمان کل ویدیو بیشتر است !", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Logo.this, "زمان شروع و پایان برش درست نمی باشد !", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Logo.this, "زمان شروع و پایان برش درست نمی باشد !", Toast.LENGTH_LONG).show();
                    }
                }


                String inputFileName = videoPlayerState.getFilename();
                path = FileUtils.getTargetFileNameLogo(inputFileName, 1);

                start_activ();

                if (coin_alfa){
                    coinint = 100;
                }
                if (coinint <= 0){
                    try {
                        end_activ();
                        (new DialogNoTicket(Logo.this)).showDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    one_play_editor.putInt("code", 8);
                    one_play_editor.putInt("one", 1);
                    one_play_editor.putInt("time", in);
                    one_play_editor.putInt("location", d);
                    one_play_editor.putString("inputFileName", inputFileName);
                    one_play_editor.putString("path", path);
                    one_play_editor.putString("Pic", MP3Path);
                    one_play_editor.putString("b_S_t", "در حال قرار دادن لوگو ...");
                    one_play_editor.putString("b_F_c", "متاسفانه در قرار دادن لوگو خطایی بوجود آمد");
                    one_play_editor.putString("b_E_t", "قرار دادن لوگو");
                    one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                    one_play_editor.commit();

                    work = one_play_preferences.getInt("work", 0);
                    if (work == 4){
                        (new DialogFollow(Logo.this)).showDialog();
                    }else if (work == 5){
                        (new DialogStar(Logo.this)).showDialog();
                    }

                    startService(new Intent(Logo.this, VideoEngine.class));
                }
            }else {
                Toast.makeText(Logo.this, "لطفا لوگو را انتخاب کنید", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void start_activ() {
        dialog = new ProgressDialog(Logo.this);
        dialog.setMessage("\u062f\u0631 \u062d\u0627\u0644 \u0627\u0646\u062c\u0627\u0645 \u06a9\u0627\u0631...");
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void end_activ() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("9"));
        }
    }

    public static void in_activ() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("10"));
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
                Logo.StateObserver.this.startVideoProgressObserving();
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

    public Logo() {
        this.LOADING_DIALOG = MY_NOTIFICATION_ID;
        this.ok = false;
        this.f77k = 0;
        this.videoPlayerState = new VideoPlayerState();
        this.path = null;
        this.duration = 0;
        this.start = 0;
        this.end = 0;
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
        intent.putExtra("activ", 7);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.logo_view);

        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("11"));
        }

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("12"));
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

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = (RadioButton)radioGroup.findViewById(i);
                boolean isChecked = r1.isChecked();
                if (isChecked){
                    st = Integer.parseInt(String.valueOf(r1.getText().length()));
                }
            }
        });

        this.rl_videoplayer = (RelativeLayout) findViewById(R.id.rl_videoplayer);
        this.trimButton = (TextView) findViewById(R.id.trimbut);
        this.trimButton.setOnClickListener(trimClickListener());
        this.vvScreen = (VideoView) findViewById(R.id.videoView1);
        this.btnPlay = (Button) findViewById(R.id.buttonply);
        this.btnPlay.setOnClickListener(this.onclickbtnPlay);
        this.rl_videoplayer.setOnClickListener(this.onclickbtnPlay);
        this.tvStartVideo = (TextView) findViewById(R.id.left_pointer);
        this.hEdit = (EditText) findViewById(R.id.hEdit);
        this.hEdit2 = (EditText) findViewById(R.id.hEdit2);
        this.minEdit = (EditText) findViewById(R.id.minEdit);
        this.minEdit2 = (EditText) findViewById(R.id.minEdit2);
        this.secEdit = (EditText) findViewById(R.id.secEdit);
        this.secEdit2 = (EditText) findViewById(R.id.secEdit2);
        this.tvEndVideo = (TextView) findViewById(R.id.right_pointer);
        this.eroor = (TextView) findViewById(R.id.error);
        this.seekVideo = (SeekBar) findViewById(R.id.sbVideo);
        this.seek_in = (SeekBar) findViewById(R.id.seek_in);
        this.seek_out = (SeekBar) findViewById(R.id.seek_out);
        this.seekVideo.setOnSeekBarChangeListener(this);
        this.seek_in.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = seek_in.getProgress();
                vvScreen.seekTo(progress);
                try {
                    hEdit.setText(formatTimeUnit_ho((long) progress));
                    minEdit.setText(formatTimeUnit_min((long) progress));
                    secEdit.setText(formatTimeUnit_sec((long) progress));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("13"));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seek_in.getProgress();
                vvScreen.seekTo(progress);
                start = progress;
                try {
                    hEdit.setText(formatTimeUnit_ho((long) progress));
                    minEdit.setText(formatTimeUnit_min((long) progress));
                    secEdit.setText(formatTimeUnit_sec((long) progress));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("14"));
                }

                try {
                    tvStartVideo.setText(formatTimeUnit((long) progress));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("15"));
                }

            }
        });

        this.seek_out.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = seek_out.getProgress();
                try {
                    hEdit2.setText(formatTimeUnit_ho((long) progress));
                    minEdit2.setText(formatTimeUnit_min((long) progress));
                    secEdit2.setText(formatTimeUnit_sec((long) progress));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("16"));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seek_out.getProgress();
                end = progress;
                try {
                    hEdit2.setText(formatTimeUnit_ho((long) progress));
                    minEdit2.setText(formatTimeUnit_min((long) progress));
                    secEdit2.setText(formatTimeUnit_sec((long) progress));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("17"));
                }

            }
        });
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogHelp(Logo.this,"لطفا ابتدا لوگو مورد نظر انتخاب کنید (توجه داشته باشید که لوگو با ابعاد اصلی قرار میگیرد) و بعد جایگاه قرار گیری لوگو را انتخاب کنید ، اگر میخواهید لوگو در زمان مشخصی نمایش داده شود گزینه زمان نمایش لوگو انتخاب کنید و زمان دلخواه را مشخص کنید\n هر گونه سوال و یا مشکلی برایتان پیش آمد از طریق پشتیبانی برایمان بفرستید .")).showDialog();
            }
        });

         face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        trimButton.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        TextView txt_in = (TextView) findViewById(R.id.txt_in);
        TextView txt_out = (TextView) findViewById(R.id.txt_out);
        TextView min = (TextView) findViewById(R.id.min);
        TextView min2 = (TextView) findViewById(R.id.min2);
        TextView sec = (TextView) findViewById(R.id.sec);
        TextView sec2 = (TextView) findViewById(R.id.sec2);
        TextView miniSec = (TextView) findViewById(R.id.miniSec);
        TextView miniSec2 = (TextView) findViewById(R.id.miniSec2);
        tvt.setTypeface(face);
        txt_in.setTypeface(face);
        txt_out.setTypeface(face);
        hEdit.setTypeface(face);
        hEdit2.setTypeface(face);
        minEdit.setTypeface(face);
        minEdit2.setTypeface(face);
        secEdit.setTypeface(face);
        secEdit2.setTypeface(face);
        min.setTypeface(face);
        min2.setTypeface(face);
        sec.setTypeface(face);
        sec2.setTypeface(face);
        miniSec.setTypeface(face);
        miniSec2.setTypeface(face);

        TextView ts_check = (TextView) findViewById(R.id.ts_check);
        ts_check.setTypeface(face);
        LinearLayout click_check = (LinearLayout) findViewById(R.id.click_check);
        final RelativeLayout hide1 = (RelativeLayout) findViewById(R.id.hide1);
        final RelativeLayout hide2 = (RelativeLayout) findViewById(R.id.hide2);
        hide1.setVisibility(View.GONE);
        hide2.setVisibility(View.GONE);
        final ImageView img_check = (ImageView) findViewById(R.id.img_check);
        ch = 0;
        click_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch == 0){
                    ch = 1;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_on);
                    hide1.setVisibility(View.VISIBLE);
                    hide2.setVisibility(View.VISIBLE);
                }else {
                    ch = 0;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_off);
                    hide1.setVisibility(View.GONE);
                    hide2.setVisibility(View.GONE);
                }
            }
        });

        TextView select = (TextView) findViewById(R.id.select);
        select.setTypeface(face);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,1);

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
            int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(idx);
            cursor.close();
            ImageView v = (ImageView)findViewById(R.id.image);
            v.setVisibility(View.VISIBLE);
            v.setImageBitmap(BitmapFactory.decodeFile(result));
        }
        return result;
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
            FirebaseCrash.report(new Exception("18"));
            return null;
        }
    }

    public static String formatTimeUnit_ho(long millis) throws ParseException {
        try {
            String out;
            long hours = millis / 3600000;
            if (hours > 0) {
                out = new StringBuilder(String.valueOf(hours)).toString();
            } else {
                out = "00";
            }
            return out;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("19"));
            return null;
        }
    }

    public static String formatTimeUnit_min(long millis) throws ParseException {
        try {
            long hours = millis / 3600000;
            long remaining_minutes = (millis - (3600000 * hours)) / 60000;
            String minutes = String.valueOf(remaining_minutes);
            if (minutes.equals(0)) {
                minutes = "00";
            }
            return minutes;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("20"));
            return null;
        }
    }

    public static String formatTimeUnit_sec(long millis) throws ParseException {
        try {
            long hours = millis / 3600000;
            long remaining_minutes = (millis - (3600000 * hours)) / 60000;
            long seconds = (millis - (3600000 * hours)) - (60000 * remaining_minutes);
            seconds = (seconds / 1000) % 60 ;
            String seconds2 = String.valueOf(seconds);
            if (seconds2.length() < 2) {
                seconds2 = seconds2.substring(0, 1);
            } else {
                seconds2 = seconds2.substring(0, 2);
            }
            return seconds2;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("21"));
            return null;
        }
    }


    public static int formatTimeUnit_ho_back(String millis) throws ParseException {
        try {
            long s = Integer.parseInt(millis);
            long hours = s * 3600000;
            return (int) hours;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("22"));
            return 0;
        }
    }

    public static int formatTimeUnit_min_back(String millis) throws ParseException {
        try {
            long s = Integer.parseInt(millis);
            long remaining_minutes = s * 60000;
            return (int) remaining_minutes;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("23"));
            return 0;
        }
    }

    public static int formatTimeUnit_sec_back(String millis) throws ParseException {
        try {
            long s = Integer.parseInt(millis);
            return (int) s;
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("24"));
            return 0;
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
            FirebaseCrash.report(new Exception("25"));
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
