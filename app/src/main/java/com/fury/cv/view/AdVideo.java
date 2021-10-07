package com.fury.cv.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.fury.cv.R;
import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by fury on 4/12/2017.
 */
public class AdVideo extends Activity {

    TextView fund_ads;
    int coinint,coin_plus,ads_plus;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int o,start,show,r;

    boolean one,end;

    @Override
    protected void onCreate( Bundle bundle )
    {
        super.onCreate( bundle );
        setContentView( R.layout.advideo );

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coinint = one_play_preferences.getInt("COIN", 0);
        ads_plus = one_play_preferences.getInt("number_show_ads", 0);
        one = one_play_preferences.getBoolean("one_video_ad_adcolony", true);
        show = 0;
        o = 0;
        start = 0;
        fund_ads = (TextView) findViewById(R.id.fund_ads);
        r = 0;
        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");

        fund_ads.setTypeface(face);

        String appKey = "fd397a30f8c27af492a6dda5e074ae8464c18d3441207e3a";
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO );


        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded() {
                Appodeal.show(AdVideo.this, Appodeal.REWARDED_VIDEO);
                Appodeal.isLoaded(Appodeal.REWARDED_VIDEO);
                Log.d("Appodeal", "onRewardedVideoLoaded");
            }
            @Override
            public void onRewardedVideoFailedToLoad() {
                if (r == 1){
                    Toast.makeText(AdVideo.this,  " Ad video is not available, try again ", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Appodeal.show(AdVideo.this, Appodeal.INTERSTITIAL);
                    Appodeal.isLoaded(Appodeal.INTERSTITIAL);
                }
                Log.d("Appodeal", "onRewardedVideoFailedToLoad");
            }
            @Override
            public void onRewardedVideoShown() {
                Log.d("Appodeal", "onRewardedVideoShown");
            }
            @Override
            public void onRewardedVideoFinished(int amount, String name) {
                coin_plus = 2 + coinint;
                ads_plus = 1 + ads_plus;
                one_play_editor.putInt("COIN", coin_plus);
                one_play_editor.putInt("number_show_ads", ads_plus);
                one_play_editor.apply();
                FirebaseCrash.report(new Exception("coin " + coin_plus + " show video ads " + ads_plus));
                if (r == 1){
                    finish();
                }else {
                    Appodeal.show(AdVideo.this, Appodeal.INTERSTITIAL);
                    Appodeal.isLoaded(Appodeal.INTERSTITIAL);
                }

                Log.d("Appodeal", "onRewardedVideoFinished");
            }
            @Override
            public void onRewardedVideoClosed(boolean finished) {
                Log.d("Appodeal", "onRewardedVideoClosed");
            }
        });



        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean isPrecache) {
                FirebaseCrash.report(new Exception("Show ADS Interstitial"));
                Log.d("Appodeal", "onInterstitialLoaded");
            }
            @Override
            public void onInterstitialFailedToLoad() {
                r = 1;
                Log.d("Appodeal", "onInterstitialFailedToLoad");
            }
            @Override
            public void onInterstitialShown() {
                Log.d("Appodeal", "onInterstitialShown");
            }
            @Override
            public void onInterstitialClicked() {
                coin_plus = 2 + coinint;
                one_play_editor.putInt("COIN", coin_plus);
                one_play_editor.apply();
                FirebaseCrash.report(new Exception("Interstitial Clicked"));
                finish();
                Log.d("Appodeal", "onInterstitialClicked");
            }
            @Override
            public void onInterstitialClosed() {
                finish();
                Log.d("Appodeal", "onInterstitialClosed");
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Appodeal.show(AdVideo.this, Appodeal.INTERSTITIAL);
        Appodeal.isLoaded(Appodeal.INTERSTITIAL);
        finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }
}
