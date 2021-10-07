package com.fury.cv.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.provider.MediaStore.Video.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.fury.cv.R;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;

public class ShareVideoActivity extends Activity implements OnSeekBarChangeListener {
    static Context ctx;
    ImageView btnBack;
    ImageView btnDel;
    Button btnPlay;
    OnClickListener btnclickPlay;
    ImageView btnshare;
    int duration;
    Handler handler;
    Boolean isFromMain;
    Boolean isPlay;
    OnClickListener onclickDelebtn;
    OnClickListener onclickbtnShare;
    private PowerManager pm;
    int position;
    RelativeLayout rl_videoplayer;
    SeekBar seekVideo;
    Runnable seekrunnable;
    TextView tvEndVideo;
    TextView txt_play_out;
    TextView tvStartVideo;
    Uri videoFile;
    String videoPath;
    VideoView vvScreen;
    private WakeLock wl;
    public static ShareVideoActivity act;

    /* renamed from: com.video.compressop.view.ShareVideoActivity.1 */
    class C10451 implements Runnable {
        C10451() {
        }

        public void run() {
            if (ShareVideoActivity.this.vvScreen.isPlaying()) {
                int curPos = ShareVideoActivity.this.vvScreen.getCurrentPosition();
                ShareVideoActivity.this.seekVideo.setProgress(curPos);
                try {
                    ShareVideoActivity.this.tvStartVideo.setText(ShareVideoActivity.formatTimeUnit((long) curPos));
                } catch (ParseException e) {
                    FirebaseCrash.report(new Exception("1"));
                }
                if (curPos == ShareVideoActivity.this.duration) {
                    ShareVideoActivity.this.seekVideo.setProgress(0);
                    ShareVideoActivity.this.tvStartVideo.setText("00:00");
                    ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
                    return;
                }
                ShareVideoActivity.this.handler.postDelayed(ShareVideoActivity.this.seekrunnable, 200);
                return;
            }
            ShareVideoActivity.this.seekVideo.setProgress(ShareVideoActivity.this.duration);
            try {
                ShareVideoActivity.this.tvStartVideo.setText(ShareVideoActivity.formatTimeUnit((long) ShareVideoActivity.this.duration));
            } catch (ParseException e2) {
                FirebaseCrash.report(new Exception("2"));
            }
            ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.2 */
    class C10462 implements OnClickListener {
        C10462() {
        }

        public void onClick(View arg0) {
            if (ShareVideoActivity.this.isPlay.booleanValue()) {
                ShareVideoActivity.this.vvScreen.pause();
                ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
                ShareVideoActivity.this.btnPlay.setBackgroundResource(R.drawable.play);
            } else {
                ShareVideoActivity.this.vvScreen.seekTo(ShareVideoActivity.this.seekVideo.getProgress());
                ShareVideoActivity.this.vvScreen.start();
                ShareVideoActivity.this.handler.postDelayed(ShareVideoActivity.this.seekrunnable, 200);
                ShareVideoActivity.this.btnPlay.setBackgroundResource(R.drawable.pause);
            }
            ShareVideoActivity.this.isPlay = Boolean.valueOf(!ShareVideoActivity.this.isPlay.booleanValue());
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.3 */
    class C10473 implements OnClickListener {
        C10473() {
        }

        public void onClick(View v) {
            if (ShareVideoActivity.this.vvScreen.isPlaying()) {
                ShareVideoActivity.this.vvScreen.pause();
                ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
                ShareVideoActivity.this.btnPlay.setBackgroundResource(R.drawable.play);
                ShareVideoActivity.this.isPlay = Boolean.valueOf(false);
            }
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.putExtra("android.intent.extra.SUBJECT", "CV (Best App)");
            sharingIntent.setType("video/*");
            sharingIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(ShareVideoActivity.this.videoPath)));
            sharingIntent.putExtra("android.intent.extra.TEXT", "video");
            ShareVideoActivity.this.startActivity(Intent.createChooser(sharingIntent, "با چه برنامه ای؟"));
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.4 */
    class C10494 implements OnClickListener {

        C10494() {
        }

        public void onClick(View v) {
            if (ShareVideoActivity.this.vvScreen.isPlaying()) {
                ShareVideoActivity.this.vvScreen.pause();
                ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
                ShareVideoActivity.this.btnPlay.setBackgroundResource(R.drawable.play);
                ShareVideoActivity.this.isPlay = Boolean.valueOf(false);
            }
            DialogUtils.showDeleteDialog(ShareVideoActivity.act, new C03981(videoPath));
        }
    }

    class C03981 implements DialogUtils.DialogBtnClickListener {
        private final /* synthetic */ String val$position;

        C03981(String i) {
            this.val$position = i;
        }

        public void onPositiveClick() {
            ShareVideoActivity.this.deleteTmpFile(val$position);
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.5 */
    class C10505 implements OnErrorListener {
        C10505() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(ShareVideoActivity.this.getApplicationContext(), "ویدیو قابل پخش نیست!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.6 */
    class C10516 implements OnPreparedListener {
        C10516() {
        }

        public void onPrepared(MediaPlayer mp) {
            ShareVideoActivity.this.duration = ShareVideoActivity.this.vvScreen.getDuration();
            ShareVideoActivity.this.seekVideo.setMax(ShareVideoActivity.this.duration);
            ShareVideoActivity.this.tvStartVideo.setText("00:00");
            try {
                ShareVideoActivity.this.tvEndVideo.setText(ShareVideoActivity.formatTimeUnit((long) ShareVideoActivity.this.duration));
            } catch (ParseException e) {
                FirebaseCrash.report(new Exception("3"));
            }
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.7 */
    class C10527 implements OnCompletionListener {
        C10527() {
        }

        public void onCompletion(MediaPlayer mp) {
            ShareVideoActivity.this.vvScreen.seekTo(100);
            ShareVideoActivity.this.btnPlay.setBackgroundResource(R.drawable.play);
            ShareVideoActivity.this.vvScreen.seekTo(0);
            ShareVideoActivity.this.seekVideo.setProgress(0);
            ShareVideoActivity.this.tvStartVideo.setText("00:00");
            ShareVideoActivity.this.handler.removeCallbacks(ShareVideoActivity.this.seekrunnable);
        }
    }

    /* renamed from: com.video.compressop.view.ShareVideoActivity.8 */
    class C10538 implements OnClickListener {
        C10538() {
        }

        public void onClick(View arg0) {
            ShareVideoActivity.this.onBackPressed();
        }
    }

    public ShareVideoActivity() {
        this.isPlay = Boolean.valueOf(false);
        this.duration = 0;
        this.handler = new Handler();
        this.isFromMain = Boolean.valueOf(true);
        this.position = 0;
        this.seekrunnable = new C10451();
        this.btnclickPlay = new C10462();
        this.onclickbtnShare = new C10473();
        this.onclickDelebtn = new C10494();
    }

    public void onBackPressed() {
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_share_video_view);
        try {
            this.pm = (PowerManager) getSystemService(POWER_SERVICE);
            this.wl = this.pm.newWakeLock(6, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("4"));
        }
        act = this;
        this.videoPath = getIntent().getStringExtra("videofilename");
        this.videoFile = Uri.parse("file://" + this.videoPath);
        ctx = this;
        findById();
        this.position = getIntent().getIntExtra("position", 0);
        this.vvScreen.setVideoPath(this.videoPath);
        this.vvScreen.seekTo(100);
        this.vvScreen.setOnErrorListener(new C10505());
        this.vvScreen.setOnPreparedListener(new C10516());
        this.vvScreen.setOnCompletionListener(new C10527());

        FirebaseCrash.log("log 1");
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
            FirebaseCrash.report(new Exception("5"));
            return null;
        }
    }

    private void findById() {
        this.btnshare = (ImageView) findViewById(R.id.btnShare);
        this.btnshare.setOnClickListener(this.onclickbtnShare);
        this.btnDel = (ImageView) findViewById(R.id.btn_delete);
        this.btnDel.setOnClickListener(this.onclickDelebtn);
        this.btnPlay = (Button) findViewById(R.id.btnPlay);
        this.btnPlay.setOnClickListener(this.btnclickPlay);
        this.vvScreen = (VideoView) findViewById(R.id.vvScreen);
        this.rl_videoplayer = (RelativeLayout) findViewById(R.id.rl_videoplayer);
        this.rl_videoplayer.setOnClickListener(this.btnclickPlay);
        this.tvStartVideo = (TextView) findViewById(R.id.left_pointer);
        this.txt_play_out = (TextView) findViewById(R.id.txt_play_out);
        this.tvEndVideo = (TextView) findViewById(R.id.right_pointer);
        this.seekVideo = (SeekBar) findViewById(R.id.sbVideo);
        this.seekVideo.setOnSeekBarChangeListener(this);
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.btnBack.setOnClickListener(new C10538());
        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        txt_play_out.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvVideolistname);
        tvt.setTypeface(face);
        txt_play_out.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(videoPath));
                intent.setDataAndType(Uri.fromFile(new File(videoPath)), "video/mp4");
                startActivity(intent);
            }
        });
    }

    public void deleteTmpFile(String videopath) {
        File file = new File(videopath);
        if (file.exists()) {
            try {
                file.delete();
                getContentResolver().delete(Media.EXTERNAL_CONTENT_URI, "_data =?", new String[]{videopath});
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("6"));
            }
            Toast.makeText(ctx, "\u062d\u0630\u0641 \u0641\u06cc\u0644\u0645 \u0628\u0627 \u0645\u0648\u0641\u0642\u06cc\u062a \u0627\u0646\u062c\u0627\u0645 \u0634\u062f.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onResume() {
        this.wl.acquire();
        super.onResume();
    }

    protected void onPause() {
        this.wl.release();
        super.onPause();
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
            FirebaseCrash.report(new Exception("7"));
        }
    }
}
