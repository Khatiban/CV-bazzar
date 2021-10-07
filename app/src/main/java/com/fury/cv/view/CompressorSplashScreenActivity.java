package com.fury.cv.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.fury.cv.PageStart.PageStart;
import com.fury.cv.R;
import com.fury.cv.util.PermissionHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.firebase.crash.FirebaseCrash;

public class CompressorSplashScreenActivity extends Activity {
    private static int SPLASH_TIME_OUT;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int one;
    /* renamed from: com.video.compressop.view.CompressorSplashScreenActivity.1 */
    class C10431 implements Runnable {
        C10431() {
        }

        public void run() {
            startActivity(new Intent(CompressorSplashScreenActivity.this, Page_org.class));
            finish();
        }
    }

    static {
        SPLASH_TIME_OUT = 3200;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lay_mainsplash_screen);

        ImageView gif = (ImageView)findViewById(R.id.gif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gif);
        Glide.with(this).load(R.raw.cv_start_3).into(imageViewTarget);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        one = one_play_preferences.getInt("onebar", 0);
        if (one == 0){
            one_play_editor.putInt("onebar", 1);
            one_play_editor.putInt("COIN", 3);
            one_play_editor.putString("S64", "01001101010010010100100001001110010011010100000100110000010001110100001101010011011100010100011101010011010010010110001000110011010001000101000101000101010000100100000101010001010101010100000101000001001101000100011100110111010000010100010001000011010000100111010001110111010010110100001001110010011101110100010001100010011011110011011101110000011100100100010101111010011010100011000101001101011100000110111101110000011001110111011001101001011101010111000100110000011001100111101000111001011010100101010001010100001010110100011100110000011011000101000101000100010100000111000101100101010011110101011000110001011101000110110101101100011001000100111100110110001100110110011101111001011101010110000101010101010010100110000101100111011100010110010001100010010101100110001100110010010101110101010001100011011010000110100001111001011101010010101101110001010100100111001101011001001100000110100001001111010001010100111101011000010101110100001001101110011110010100111101101010011011110101000001000011011000010100000101101101011100110111001101010010010110000110010001100100011101110110111001001100010001110100011101001110011110010101101000110001010001010011100101000011011001000111011101001001011011100101101001100110001110000111010001100100010100010111101001000001010101100110101001100010010011100110101001110000010001000101000100110011011001100100001000110000001110000101001101000001011001010110100001100100010000110100111001110110001011110101001001101000010011010101010101001001010011110111011001101110010011010011100001110111010010010110111100110111011011110100100101001000010101100110011001100010011100110100111100111001010000010101000101101010011000010011011101111001001101000111011100110011010010010110010101101000011100010010101101111000010110010011001001111010011100100110101001100110011101000110111001111000011110100100001001101100010100000100111101101110010001000111010001111010001101110100111100111001010001000011100000101011001100010100010101101111011000110100011000110010011001010011100000110111011011000101010100110010011001100110100001011010011010100110111001101000001101110111010000101111011000010101011101101101011011000110101001101100010011010100001001010101010000110100000101110111010001010100000101000001010100010011110100111101");
            one_play_editor.apply();

            startActivity(new Intent(CompressorSplashScreenActivity.this, PageStart.class));
            finish();

        }else {

            try {
                new Handler().postDelayed(new C10431(), (long) SPLASH_TIME_OUT);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("Handler"));
            }
        }
        FirebaseCrash.log("log 1");
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }

    private void checkPermissions(){

        String[] per = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};

        new PermissionHandler().checkPermission(this, per, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                // permission granted
                // your code
                new Handler().postDelayed(new C10431(), (long) 1000);
            }

            @Override
            public void onPermissionDenied() {
                // User canceled permission
                Toast.makeText(CompressorSplashScreenActivity.this,"در صورت نپذیرفتن درخواست ها برنامه با مشکل مواجه می شود!", Toast.LENGTH_LONG).show();
            }
        });


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
