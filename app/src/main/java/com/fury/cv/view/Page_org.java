package com.fury.cv.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.Suport.Suport_me;
import com.fury.cv.util.PermissionHandler;
import com.fury.cv.util.app_net;
import com.farsitel.bazaar.IUpdateCheckService;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fury on 5/3/2017.
 */
public class Page_org extends Activity {

    private PowerManager pm;

    private PowerManager.WakeLock wl;

    private File f78f;

    DrawerLayout drawerlayout;

    TextView tedad_coin_text,CoinSingle,lin_menu_sp,lin_menu_d,lin_menu_dm,lin_menu_gu,lin_menu_sit,lin_menu_ab,lin_menu_dm2,lin_menu_dm3,lin_menu_dm4,lin_menu_dm5,lin_menu_dm6,lin_menu_dm7,lin_menu_dm8;

    ImageView btnBack,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_11,image_12;

    ScheduledThreadPoolExecutor mDialogDaemon_time;
    String coin;
    int coinint;

    @Override
    protected void onResume() {
        wl.acquire();
        super.onResume();
    }

    @Override
    protected void onPause() {
        wl.release();
        super.onPause();
    }

    IUpdateCheckService service;
    UpdateServiceConnection connection;
    private static final String TAG = "UpdateCheck";

    class UpdateServiceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IUpdateCheckService.Stub
                    .asInterface((IBinder) boundService);
            try {
                long vCode = service.getVersionCode("com.fury.cv");
                if (vCode == 1) {
                    (new DialogUpdate(Page_org.this)).showDialog();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }
    }

    Boolean coin_alfa;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);

        setContentView(R.layout.root_1);

        try {
            pm = (PowerManager) getSystemService(POWER_SERVICE);
            wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("pm"));
        }
        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name));
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f CV"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Compressed");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Compressed"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Format");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Format"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Gif");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Gif"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Music");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Music"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Sound");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Sound"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Cut");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Cut"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Join");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Join"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Logo");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Logo"));
        }

        try {
            f78f = new File(Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.folder_name) + "/" + "Create");
            if (!f78f.exists()) {
                f78f.mkdirs();
            }
        } catch (Resources.NotFoundException e) {
            FirebaseCrash.report(new Exception("f78f Create"));
        }

        lin_menu_sp = (TextView)findViewById(R.id.lin_menu_sp);
        lin_menu_d = (TextView)findViewById(R.id.lin_menu_d);
        lin_menu_dm = (TextView)findViewById(R.id.lin_menu_dm);
        lin_menu_dm2 = (TextView)findViewById(R.id.lin_menu_dm2);
        lin_menu_dm3 = (TextView)findViewById(R.id.lin_menu_dm3);
        lin_menu_dm4 = (TextView)findViewById(R.id.lin_menu_dm4);
        lin_menu_dm5 = (TextView)findViewById(R.id.lin_menu_dm5);
        lin_menu_dm6 = (TextView)findViewById(R.id.lin_menu_dm6);
        lin_menu_dm7 = (TextView)findViewById(R.id.lin_menu_dm7);
        lin_menu_dm8= (TextView)findViewById(R.id.lin_menu_dm8);
        lin_menu_gu = (TextView)findViewById(R.id.lin_menu_gu);
        lin_menu_sit = (TextView)findViewById(R.id.lin_menu_sit);
        lin_menu_ab = (TextView)findViewById(R.id.lin_menu_ab);
        CoinSingle = (TextView)findViewById(R.id.tedad_coin);
        tedad_coin_text = (TextView)findViewById(R.id.tedad_coin_text);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        image_1 = (ImageView) findViewById(R.id.image_1);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        image_4 = (ImageView) findViewById(R.id.image_4);
        image_5 = (ImageView) findViewById(R.id.image_5);
        image_6 = (ImageView) findViewById(R.id.image_6);
        image_7 = (ImageView) findViewById(R.id.image_7);
        image_8 = (ImageView) findViewById(R.id.image_8);
        image_9 = (ImageView) findViewById(R.id.image_9);
        image_10 = (ImageView) findViewById(R.id.image_10);
        image_11 = (ImageView) findViewById(R.id.image_11);
        image_12 = (ImageView) findViewById(R.id.image_12);

        try {
            Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
            lin_menu_sp.setTypeface(face);
            CoinSingle.setTypeface(face);
            tedad_coin_text.setTypeface(face);
            lin_menu_d.setTypeface(face);
            lin_menu_dm.setTypeface(face);
            lin_menu_dm2.setTypeface(face);
            lin_menu_dm3.setTypeface(face);
            lin_menu_dm4.setTypeface(face);
            lin_menu_dm5.setTypeface(face);
            lin_menu_dm6.setTypeface(face);
            lin_menu_dm7.setTypeface(face);
            lin_menu_dm8.setTypeface(face);
            lin_menu_gu.setTypeface(face);
            lin_menu_sit.setTypeface(face);
            lin_menu_ab.setTypeface(face);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("Typeface"));
        }

        if (coin_alfa) {
            CoinSingle.setText("بی نهایت");
        } else {

            if (mDialogDaemon_time != null) {
                try {
                    mDialogDaemon_time.shutdown();
                    mDialogDaemon_time = null;
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("12"));
                }
            }
            try {
                mDialogDaemon_time = new ScheduledThreadPoolExecutor(1);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("13"));
            }
            try {
                mDialogDaemon_time.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try{
                                    coinint = one_play_preferences.getInt("COIN", 0);
                                    coin = String.valueOf(coinint);
                                    CoinSingle.setText(coin);
                                }catch (Exception e){
                                    mDialogDaemon_time.shutdown();
                                    mDialogDaemon_time = null;
                                    FirebaseCrash.report(new Exception("14"));
                                }

                            }
                        });
                    }
                }, 0L, 2000L, TimeUnit.MILLISECONDS);

            } catch (Exception e) {
                mDialogDaemon_time.shutdown();
                mDialogDaemon_time = null;
                FirebaseCrash.report(new Exception("14"));
            }

        }


        //sliding view
        drawerlayout = (DrawerLayout) findViewById(R.id.root_page_1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerlayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerlayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerlayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        lin_menu_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, VideoListActivity.class));
            }
        });

        lin_menu_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Format_list.class));
            }
        });

        lin_menu_dm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Page_org.this,SelectVideoActivity.class);
                n.putExtra("activ",3);
                startActivity(n);
            }
        });

        lin_menu_dm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, mp3_list.class));
            }
        });

        lin_menu_dm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, voice_list.class));
            }
        });

        lin_menu_dm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, cut_list.class));
            }
        });

        lin_menu_dm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, join_list.class));
            }
        });

        lin_menu_dm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, logo_list.class));
            }
        });

        lin_menu_dm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Coming_Soon.class));//create_list
            }
        });

        lin_menu_gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Store.class));
            }
        });

        lin_menu_sit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Suport_me.class));
            }
        });

        lin_menu_ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogAbout(Page_org.this)).showDialog();
            }
        });

        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, VideoListActivity.class));
            }
        });

        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Format_list.class));
            }
        });

        image_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Page_org.this,SelectVideoActivity.class);
                n.putExtra("activ",3);
                startActivity(n);
            }
        });

        image_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, mp3_list.class));
            }
        });

        image_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, voice_list.class));
            }
        });

        image_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, cut_list.class));
            }
        });

        image_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, join_list.class));
            }
        });

        image_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, logo_list.class));
            }
        });

        image_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Coming_Soon.class));///create_list
            }
        });

        image_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Store.class));
            }
        });

        image_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_org.this, Suport_me.class));
            }
        });

        image_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogAbout(Page_org.this)).showDialog();
            }
        });

        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                super.run();
                checkPermissions();
            }
        }, 1000);


        if (app_net.getInstance(Page_org.this).isOnline()) {

            initService();

        }

        FirebaseCrash.log("log 1");
    }

    private void checkPermissions(){

        String[] per = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE};

        new PermissionHandler().checkPermission(this, per, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                // permission granted
                // your code
            }

            @Override
            public void onPermissionDenied() {
                // User canceled permission
                Toast.makeText(Page_org.this,"در صورت نپذیرفتن درخواست ها برنامه با مشکل مواجه می شود!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initService() {
        connection = new UpdateServiceConnection();
        Intent i = new Intent(
                "com.farsitel.bazaar.service.UpdateCheckService.BIND");
        i.setPackage("com.farsitel.bazaar");
        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent("PERMISSION_RECEIVER");
        intent.putExtra("requestCode",requestCode);
        intent.putExtra("permissions",permissions);
        intent.putExtra("grantResults",grantResults);
        sendBroadcast(intent);
    }

}
