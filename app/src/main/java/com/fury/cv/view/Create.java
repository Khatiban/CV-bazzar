package com.fury.cv.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.multipleimageselect.activities.AlbumSelectActivity;
import com.fury.cv.multipleimageselect.helpers.Constants;
import com.fury.cv.multipleimageselect.models.Image;
import com.fury.cv.util.FileUtils;
import com.fury.cv.util.VideoEngine;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fury on 5/17/2017.
 */
public class Create extends Activity {

    ArrayList<Image> images;
    StringBuffer stringBuffer;
    private static final int MY_NOTIFICATION_ID = 1;
    protected final int LOADING_DIALOG;
    ImageView btnBack;
    ImageView btnSetting;
    EditText NameEdit, NameEdit2;
    String MP3Path;
    int ol = 0;
    static ProgressDialog dialog;
    File root;
    int duration;
    Handler handler;
    String ad,filename;
    public static Create act;
    int f77k, ch,coinint;
    boolean ok;
    Boolean plypush,coin_alfa;
    String te1, te2, te3, te4, te5, te6, te7, te8, te9, te10, te11, te12, te13, te14, te15,
            te16, te17, te18, te19, te20;
    private PowerManager pm;
    TextView trimButton;
    public static TextView text_help;
    int model = 1;
    private PowerManager.WakeLock wl;
    String path;
    int ho = 0;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int adada,work;
    Typeface face;

    /* renamed from: com.video.compressop.view.Format.7 */
    class C10617 implements View.OnClickListener {
        C10617() {
        }

        public void onClick(View arg0) {
            Create.this.onBackPressed();
        }
    }

    /* renamed from: com.video.compressop.view.Format.8 */
    class C10628 implements View.OnClickListener {
        C10628() {
        }

        public void onClick(View arg0) {
            if (ho != 0) {

                try {
                    stopService(new Intent(Create.this, VideoEngine.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String name = NameEdit.getText().toString();
                String name1 = NameEdit2.getText().toString();
                int time = Integer.parseInt(name1);
                if (time == 0) {
                    Toast.makeText(Create.this, "لطفا زمان نمایش هر عکس را مشخص کنید", Toast.LENGTH_LONG).show();
                    return;
                }

                if (name.length() > 1) {
                    if (model == 1) {
                        stringBuffer = new StringBuffer();
                        if (ho == 1) {
                            stringBuffer.append("file " + "\'" + images.get(0).path + "\'" + "\n" + "duration " + name1 + "\n");
                            root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Pic");
                            if (!root.exists()) {
                                root.mkdirs();
                            }
                            filename = String.valueOf(new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Pic/img001.jpg"));
                            copyfile(images.get(0).path , filename);
                        } else {
                            for (int i = 0; i < ho; i++) {
                                stringBuffer.append("file " + "\'" + images.get(i).path + "\'" + "\n" + "duration " + name1 + "\n");
                                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Pic");
                                if (!root.exists()) {
                                    root.mkdirs();
                                }
                                int o = i + 1;
                                String num = "00" + o;
                                if (num.length() > 3){
                                    int s = num.length();
                                    int w ;
                                    w = s - 3;
                                    num = num.substring(w, s);
                                    Toast.makeText(Create.this, num, Toast.LENGTH_SHORT).show();
                                }

                                filename = String.valueOf(new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Pic/img"+num+".jpg"));
                                copyfile(images.get(i).path , filename);
                            }
                        }

                        String name_now = null;
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
                            Date now = new Date();
                            String hours = String.valueOf(now.getHours());
                            String minutes = String.valueOf(now.getMinutes());
                            String seconds = String.valueOf(now.getSeconds());
                            String fileName = formatter.format(now);
                            name_now = fileName + "_" + hours + "_" + minutes + "_" + seconds + ".txt";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            generateNoteOnSD(getApplicationContext(), name_now, stringBuffer.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        ad = String.valueOf(new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Pic/img%3d.jpg"));
                    } else {
                        if (ho != 20 && ho != 10 && ho != 5) {
                            Toast.makeText(Create.this, "تعداد عکس ها مجاز نیست !", Toast.LENGTH_LONG).show();
                            return;
                        } else if (ho == 20) {
                            Toast.makeText(Create.this, images.get(0).path, Toast.LENGTH_LONG).show();
                            te1 = images.get(0).path;
                            te1 = images.get(1).path;
                            te2 = images.get(2).path;
                            te3 = images.get(3).path;
                            te4 = images.get(4).path;
                            te5 = images.get(5).path;
                            te6 = images.get(6).path;
                            te7 = images.get(7).path;
                            te8 = images.get(8).path;
                            te9 = images.get(9).path;
                            te10 = images.get(10).path;
                            te11 = images.get(11).path;
                            te12 = images.get(12).path;
                            te13 = images.get(13).path;
                            te14 = images.get(14).path;
                            te15 = images.get(15).path;
                            te16 = images.get(16).path;
                            te17 = images.get(17).path;
                            te18 = images.get(18).path;
                            te19 = images.get(19).path;
                        } else if (ho == 10) {
                            te1 = images.get(0).path;
                            te2 = images.get(1).path;
                            te3 = images.get(2).path;
                            te4 = images.get(3).path;
                            te5 = images.get(4).path;
                            te6 = images.get(5).path;
                            te7 = images.get(6).path;
                            te8 = images.get(7).path;
                            te9 = images.get(8).path;
                            te10 = images.get(9).path;
                        } else if (ho == 5) {
                            te1 = images.get(0).path;
                            te2 = images.get(1).path;
                            te3 = images.get(2).path;
                            te4 = images.get(3).path;
                            te5 = images.get(4).path;
                        }
                    }

                    if (MP3Path == null) {
                        ol = 1;
                    } else {
                        ol = 0;
                    }

                    try {
                        path = FileUtils.getTargetFileNameCreate(name, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        start_activ();
                        if (model != 1) {
                            one_play_editor.putString("te1", te1);
                            one_play_editor.putString("te2", te2);
                            one_play_editor.putString("te3", te3);
                            one_play_editor.putString("te4", te4);
                            one_play_editor.putString("te5", te5);
                            one_play_editor.putString("te6", te6);
                            one_play_editor.putString("te7", te7);
                            one_play_editor.putString("te8", te8);
                            one_play_editor.putString("te9", te9);
                            one_play_editor.putString("te10", te10);
                            one_play_editor.putString("te11", te11);
                            one_play_editor.putString("te12", te12);
                            one_play_editor.putString("te13", te13);
                            one_play_editor.putString("te14", te14);
                            one_play_editor.putString("te15", te15);
                            one_play_editor.putString("te16", te16);
                            one_play_editor.putString("te17", te17);
                            one_play_editor.putString("te18", te18);
                            one_play_editor.putString("te19", te19);
                            one_play_editor.putString("te20", te20);
                            one_play_editor.commit();
                        }
                        one_play_editor.putInt("code", 9);
                        one_play_editor.putInt("one", 1);
                        one_play_editor.putString("inputFileName", ad);
                        one_play_editor.putString("MP3Path", MP3Path);
                        one_play_editor.putInt("ho", ho);
                        one_play_editor.putInt("model", model);
                        one_play_editor.putInt("ol", ol);
                        one_play_editor.putInt("time", time);
                        one_play_editor.putString("path", path);
                        one_play_editor.putString("b_S_t", "در حال ساخت ویدیو ...");
                        one_play_editor.putString("b_F_c", "متاسفانه ساخت ویدیو انجام نشد!");
                        one_play_editor.putString("b_E_t", "ساختن ویدیو");
                        one_play_editor.putString("b_E_c", "با موفقیت به پایان رسید");
                        one_play_editor.commit();

                        if (coin_alfa){
                            coinint = 100;
                        }
                        if (coinint <= 0){
                            try {
                                end_activ();
                                (new DialogNoTicket(Create.this)).showDialog();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {

                            work = one_play_preferences.getInt("work", 0);
                            if (work == 4){
                                (new DialogFollow(Create.this)).showDialog();
                            }else if (work == 5){
                                (new DialogStar(Create.this)).showDialog();
                            }

                            startService(new Intent(Create.this, VideoEngine.class));

                        }
                    } catch (Exception e) {
                        Toast.makeText(Create.this, "error 2", Toast.LENGTH_LONG).show();
                        File appVoice = new File(Create.this.path);
                        if (appVoice.exists()) {
                            appVoice.delete();
                            Create.this.finish();
                        }
                    }
                } else {
                    Toast.makeText(Create.this, "لطفا برای ویدیو یک نام بگذارید", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(Create.this, "لطفا برای ساخت ویدیو عکس هایی را انتخاب کنید", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void start_activ() {
        dialog = new ProgressDialog(Create.this);
        dialog.setMessage("\u062f\u0631 \u062d\u0627\u0644 \u0627\u0646\u062c\u0627\u0645 \u06a9\u0627\u0631...");
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void end_activ() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void in_activ() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Create() {
        this.LOADING_DIALOG = MY_NOTIFICATION_ID;
        this.ok = false;
        this.f77k = 0;
        this.duration = 0;
        this.handler = new Handler();
        this.plypush = Boolean.valueOf(false);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VideoView", "In on create");
        act = this;
        setContentView(R.layout.create_view);
        adada = 0;
        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        this.pm = (PowerManager) getSystemService(POWER_SERVICE);
        this.wl = this.pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        findById();
        FirebaseCrash.log("log 1");
    }

    private void findById() {
        this.trimButton = (TextView) findViewById(R.id.trimbut);
        TextView music = (TextView) findViewById(R.id.music);
        this.trimButton.setOnClickListener(trimClickListener());
        this.btnBack = (ImageView) findViewById(R.id.btnBack);
        this.NameEdit = (EditText) findViewById(R.id.NameEdit);
        this.NameEdit2 = (EditText) findViewById(R.id.NameEdit2);
        this.btnSetting = (ImageView) findViewById(R.id.btnSetting);
        this.btnBack.setOnClickListener(new C10617());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        trimButton.setTypeface(face);
        TextView tvt = (TextView) findViewById(R.id.tvt);
        text_help = (TextView) findViewById(R.id.text_help);
        TextView ts_check = (TextView) findViewById(R.id.ts_check);
        TextView select2 = (TextView) findViewById(R.id.select2);
        TextView txtname = (TextView) findViewById(R.id.txtname);
        TextView select = (TextView) findViewById(R.id.select);
        final TextView model_1 = (TextView) findViewById(R.id.model_1);
        final TextView model_2 = (TextView) findViewById(R.id.model_2);
        final TextView model_3 = (TextView) findViewById(R.id.model_3);
        final TextView text_tedad = (TextView) findViewById(R.id.text_tedad);
        TextView textmodel = (TextView) findViewById(R.id.textmodel);
        TextView text_time = (TextView) findViewById(R.id.text_time);
        final ImageView img_check = (ImageView) findViewById(R.id.img_check);
        LinearLayout click_check = (LinearLayout) findViewById(R.id.click_check);
        LinearLayout col_help = (LinearLayout) findViewById(R.id.col_help);
        tvt.setTypeface(face);
        select2.setTypeface(face);
        NameEdit.setTypeface(face);
        NameEdit2.setTypeface(face);
        music.setTypeface(face);
        textmodel.setTypeface(face);
        text_time.setTypeface(face);
        model_1.setTypeface(face);
        text_tedad.setTypeface(face);
        model_2.setTypeface(face);
        model_3.setTypeface(face);
        txtname.setTypeface(face);
        ts_check.setTypeface(face);
        select.setTypeface(face);

        model_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model_1.setBackgroundResource(R.drawable.back_voice);
                model_2.setBackgroundResource(R.drawable.back_voice_off);
                model_3.setBackgroundResource(R.drawable.back_voice_off);
                text_tedad.setVisibility(View.GONE);
                model = 1;
            }
        });
        model_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model_1.setBackgroundResource(R.drawable.back_voice_off);
                model_2.setBackgroundResource(R.drawable.back_voice);
                model_3.setBackgroundResource(R.drawable.back_voice_off);
                text_tedad.setVisibility(View.VISIBLE);
                model = 2;
            }
        });
        model_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model_1.setBackgroundResource(R.drawable.back_voice_off);
                model_2.setBackgroundResource(R.drawable.back_voice_off);
                model_3.setBackgroundResource(R.drawable.back_voice);
                text_tedad.setVisibility(View.VISIBLE);
                model = 3;
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 5);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Create.this, AlbumSelectActivity.class);
//set limit on number of images that can be selected, default is 10
                int numberOfImagesToSelect;
                if (model == 1) {
                    numberOfImagesToSelect = 100;
                } else {
                    numberOfImagesToSelect = 20;
                }
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, numberOfImagesToSelect);
                startActivityForResult(intent, Constants.REQUEST_CODE);

                //Intent i = new Intent();
                //i.setType("image/*");
                //i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                //i.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(Intent.createChooser(i,"انتخاب عکس"),PICK_IMAGE_MULTIPLE);

            }
        });

        int check = one_play_preferences.getInt("check_7", 0);
        if (check == 1) {
            col_help.setVisibility(View.GONE);
        }
        ch = 0;
        click_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch == 0) {
                    ch = 1;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_on);
                    one_play_editor.putInt("check_7", 1);
                    one_play_editor.apply();
                } else {
                    ch = 0;
                    img_check.setImageResource(R.drawable.sport_me_tic_music_off);
                    one_play_editor.putInt("check_7", 0);
                    one_play_editor.apply();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {

                /*ClipData mclipdata = data.getClipData();
                if (model != 1) {
                    if (mclipdata.getItemCount() != 20 && mclipdata.getItemCount() != 10 && mclipdata.getItemCount() != 5) {
                        Toast.makeText(this, "تعداد عکس های انتخاب شده مجاز نیست!", Toast.LENGTH_LONG).show();
                    }
                }*/

                images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);

            stringBuffer = new StringBuffer();
            ho = images.size();
            for (int i = 0, l = images.size(); i < l; i++) {
                stringBuffer.append(images.get(i).path + "\n");
            }

        } else if (requestCode == 5 && resultCode == RESULT_OK && null != data) {

            Uri audio = data.getData(); //declared above Uri audio;
            MP3Path = String.valueOf(new File(getRealPathFromURI(audio)));
            Log.d("media", "onActivityResult: " + audio);

        } else {
            Toast.makeText(this, "هیچی انتخاب نشد!", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getRealPathFromURI2(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
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

    class C03981 implements DialogUtils.DialogBtnClickListener_set {

        C03981() {
        }

        public void onPositiveClick(String s) {

        }
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {

            root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Code");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }

    private void copyfile(String input , String output){
        InputStream in = null;
        OutputStream out = null;
        try{
            in = new FileInputStream(input);
            out = new FileOutputStream(output);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1){
                out.write(buffer,0,read);
            }
            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;

        }catch (FileNotFoundException fi){
            fi.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
