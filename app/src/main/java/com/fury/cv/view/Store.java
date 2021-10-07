package com.fury.cv.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.adapter.MyDatabaseHelper;
import com.fury.cv.util.app_net;
import com.fury.cv.util.util.IabHelper;
import com.fury.cv.util.util.IabResult;
import com.fury.cv.util.util.Inventory;
import com.fury.cv.util.util.Purchase;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Store extends AppCompatActivity implements View.OnClickListener {

    ImageView card_coin_1000, card_coin_2000, card_coin_5000, card_coin_9000, card_coin_8;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    int coin_plus, outs,freeint;
    String tedad_coin;

    ImageView back_page_video;

    Boolean coin_alfa,click,start,log,not_one_ads;

    TextView  CoinSingle;

    // Debug tag, for logging
    // Debug tag, for logging

    ScrollView scrollView;
    static Typeface face;

    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_PREMIUM = "20tiket";
    static final String SKU_PREMIUM2 = "50tiket";
    static final String SKU_PREMIUM3 = "100tiket";
    static final String SKU_PREMIUM4 = "ben";
    //static final String SKU_PREMIUM5 = "pmsce";
    //static final String SKU_PREMIUM6 = "benhayat";

    static String SKU ,SCheck ,b64key;

    // Does the user have the premium upgrade?
    boolean mIsPremium = false;

    int coinint,ads_plus;

    // The helper object
    IabHelper mHelper;

    int nov = 0;

    File root, root1, root2, root3;

    private ScheduledThreadPoolExecutor mDialogDaemon_time,mDialogDaemon_coin;

    MyDatabaseHelper key_64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        key_64 = new MyDatabaseHelper(this);
        StringBuffer dadeha = new StringBuffer();
        Cursor res_2 = key_64.showAllData();
        while (res_2.moveToNext()) {
            dadeha.append(res_2.getString(1));
        }
        b64key = dadeha.toString();
        String output = "";
        for (int i = 0; i <= b64key.length() - 8 ; i+=8){
            int k = Integer.parseInt(b64key.substring(i,i+8),2);
            output+=(char)k;
        }
        mHelper = new IabHelper(this, output);
        setContentView(R.layout.content_store);

        root = new File(Environment.getExternalStorageDirectory(), "CV/Log/oo");
        root1 = new File(Environment.getExternalStorageDirectory(), "CV/Log/ooooo");
        root2 = new File(Environment.getExternalStorageDirectory(), "CV/Log/oooooooooo");
        root3 = new File(Environment.getExternalStorageDirectory(), "CV/Log/o");

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        SCheck = one_play_preferences.getString("S64", "");

        click = false;
        start = false;
        log = false;
        not_one_ads = false;

        card_coin_1000 = (ImageView) findViewById(R.id.tiket_1);
        card_coin_2000 = (ImageView) findViewById(R.id.tiket_2);
        card_coin_5000 = (ImageView) findViewById(R.id.tiket_3);
        card_coin_9000 = (ImageView) findViewById(R.id.tiket_4);
        card_coin_8 = (ImageView) findViewById(R.id.tiket_5);
        CoinSingle = (TextView) findViewById(R.id.CoinSingle);
        back_page_video = (ImageView) findViewById(R.id.back_page_video);
        scrollView = (ScrollView) findViewById(R.id.scroll);

        card_coin_1000.setOnClickListener(this);
        card_coin_2000.setOnClickListener(this);
        card_coin_5000.setOnClickListener(this);
        card_coin_9000.setOnClickListener(this);
        card_coin_8.setOnClickListener(this);
        back_page_video.setOnClickListener(this);

        face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");

        coinint = one_play_preferences.getInt("COIN", 0);

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

                            coinint = one_play_preferences.getInt("COIN", 0);
                            tedad_coin = String.valueOf(coinint);
                            CoinSingle.setText(tedad_coin);

                        }
                    });
                }
            }, 0L, 2000L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            FirebaseCrash.report(new Exception("14"));
        }
        check_key(dadeha.toString());
        String output2 = "";
        for (int i = 0; i <= SCheck.length() - 8 ; i+=8){
            int k = Integer.parseInt(SCheck.substring(i,i+8),2);
            output2+=(char)k;
        }
        check_64(output2,output);
        tedad_coin = String.valueOf(coinint);
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);


        if (coin_alfa) {
            CoinSingle.setText("بی نهایت");
        } else {
            CoinSingle.setText(tedad_coin);
        }


// You can find it in your Bazaar console, in the Dealers section.
// It is recommended to add more security than just pasting it in your source code;
        mHelper.enableDebugLogging(true);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                }
                // Hooray, IAB is fully set up!
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });


        FirebaseCrash.log("log 1");
        System.gc();

    }



    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (result.isFailure()) {
                return;
            } else {
                // does the user have the premium upgrade?
                mIsPremium = inventory.hasPurchase(SKU_PREMIUM);
                if (mIsPremium) {
                    MasrafSeke(inventory.getPurchase(SKU_PREMIUM));
                }

                mIsPremium = inventory.hasPurchase(SKU_PREMIUM2);
                if (mIsPremium) {
                    MasrafSeke(inventory.getPurchase(SKU_PREMIUM2));
                }

                mIsPremium = inventory.hasPurchase(SKU_PREMIUM3);
                if (mIsPremium) {
                    MasrafSeke(inventory.getPurchase(SKU_PREMIUM3));
                }

                mIsPremium = inventory.hasPurchase(SKU_PREMIUM4);
                if (mIsPremium) {
                    MasrafSeke(inventory.getPurchase(SKU_PREMIUM4));
                }

                // update UI accordingly

            }

        }
    };




    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_page_video:
                finish();
                break;
            case R.id.tiket_1:

                freeint = one_play_preferences.getInt("free", 0);

                if (app_net.getInstance(Store.this).isOnline()) {

                    if (freeint == 1){
                        (new DialogTel(Store.this)).showDialog();
                    }else {
                        Intent adv = new Intent(this,AdVideo.class);
                        startActivity(adv);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "اینترنت قطع می باشد", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.tiket_2:

                if (app_net.getInstance(Store.this).isOnline()) {

                    SKU = "20tiket";
                    nov = 1;

                    try{
                        mHelper.launchPurchaseFlow(this, SKU, 1001, mPurchaseFinishedListener, "payload-string");
                    }catch (Exception e){
                        Toast.makeText(Store.this, "لطفا کمی صبر کنید و دوباره کلیک کنید", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "اینترنت قطع می باشد", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.tiket_3:
                if (app_net.getInstance(Store.this).isOnline()) {

                    SKU = "50tiket";
                    nov = 2;

                    try{
                        mHelper.launchPurchaseFlow(this, SKU, 1001, mPurchaseFinishedListener, "payload-string");
                    }catch (Exception e){
                        Toast.makeText(Store.this, "لطفا کمی صبر کنید و دوباره کلیک کنید", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "اینترنت قطع می باشد", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.tiket_4:
                if (app_net.getInstance(Store.this).isOnline()) {

                    SKU = "100tiket";
                    nov = 3;

                    try{
                        mHelper.launchPurchaseFlow(this, SKU, 1001, mPurchaseFinishedListener, "payload-string");
                    }catch (Exception e){
                        Toast.makeText(Store.this, "لطفا کمی صبر کنید و دوباره کلیک کنید", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "اینترنت قطع می باشد", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.tiket_5:
                if (app_net.getInstance(Store.this).isOnline()) {

                    SKU = "ben";
                    nov = 4;

                    try{
                        mHelper.launchPurchaseFlow(this, SKU, 1001, mPurchaseFinishedListener, "payload-string");
                    }catch (Exception e){
                        Toast.makeText(Store.this, "لطفا کمی صبر کنید و دوباره کلیک کنید", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "اینترنت قطع می باشد", Toast.LENGTH_LONG).show();
                }

                break;

        }
    }


    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                return;
            } else if (purchase.getSku().equals(SKU)) {
                if (nov == 1){
                    get_coin(20,1);
                }else if (nov == 2){
                    get_coin(55,2);
                }else if (nov == 3){
                    get_coin(120,3);
                }else if (nov == 4){
                    get_coin(8,6);
                }else {
                    Toast.makeText(Store.this, "خرید ناموفق بود", Toast.LENGTH_SHORT).show();
                }
                MasrafSeke(purchase);
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }


    private void MasrafSeke(Purchase kala) {
        mHelper.consumeAsync(kala, new IabHelper.OnConsumeFinishedListener() {
            @Override
            public void onConsumeFinished(Purchase purchase, IabResult result) {
            }
        });
    }


    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {

            root = new File(Environment.getExternalStorageDirectory(), "CV/Log");
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

    public void anim_coin(final int a) {

        int b=1 ;

       outs = 1;

        if (mDialogDaemon_coin != null) {
            try {
                mDialogDaemon_coin.shutdown();
                mDialogDaemon_coin = null;
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("12"));
            }
        }
        try {
            mDialogDaemon_coin = new ScheduledThreadPoolExecutor(1);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("13"));
        }
        try {
            final int finalB = b;
            mDialogDaemon_coin.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                if ( outs < a ) {
                                    outs = outs + finalB;
                                    final int finalS = outs;
                                    String n = String.valueOf(finalS);
                                    CoinSingle.setText(n);
                                }else {
                                    mDialogDaemon_coin.shutdown();
                                    mDialogDaemon_coin = null;
                                }
                            }catch (Exception e){
                                String n = String.valueOf(a);
                                CoinSingle.setText(n);
                            }


                        }
                    });
                }
            }, 0L, 100L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            String n = String.valueOf(a);
            CoinSingle.setText(n);
            FirebaseCrash.report(new Exception("14"));
        }
    }

    public void get_coin(int a,int u) {
        try {
            if (u == 6){
                CoinSingle.setText("بی نهایت");
                one_play_editor.putBoolean("COIN_Alfa", true);
                one_play_editor.apply();
                Toast.makeText(Store.this, "خرید موفق", Toast.LENGTH_SHORT).show();
            }else {
                coin_plus = a + coinint;
                one_play_editor.putInt("COIN", coin_plus);
                one_play_editor.apply();
                anim_coin(coin_plus);
                Toast.makeText(Store.this, "خرید موفق", Toast.LENGTH_SHORT).show();
            }

            if (u == 1){
                if (!root.exists()) {
                    generateNoteOnSD(getApplicationContext(), "oo", "junhn s;i 20");
                }
                FirebaseCrash.report(new Exception("20 T anjamshod"));
            }else if (u == 2){
                (new DialogCoinGift(Store.this, "شما برنده 5 بلیط رایگان شده اید!", "یعنی 50 بلیط خریدی ، 5 تا هم مجانی گرفتی!")).showDialog();
                if (!root1.exists()) {
                    generateNoteOnSD(getApplicationContext(), "ooooo", "junhn s;i 55");
                }
                FirebaseCrash.report(new Exception("55 T anjamshod"));
            }else if (u == 3){
                (new DialogCoinGift(Store.this, "شما برنده 20 بلیط رایگان شده اید!", "یعنی 100 بلیط خریدی ، 20 تا هم مجانی گرفتی!")).showDialog();
                if (!root2.exists()) {
                    generateNoteOnSD(getApplicationContext(), "oooooooooo", "junhn s;i 120");
                }
                FirebaseCrash.report(new Exception("120 T anjamshod"));
            }

        } catch (Exception e) {
            if (u == 6){
                one_play_editor.putBoolean("COIN_Alfa", true);
                one_play_editor.apply();
            }else {
                one_play_editor.putInt("COIN", a + coinint);
                one_play_editor.apply();
                Toast.makeText(Store.this, "خرید موفق", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void check_key(String s){
        if (!Objects.equals(s, SCheck)){
            FirebaseCrash.report(new Exception("H_shod_" + getPackageName()));
            System.exit(0);
        }
    }

    public void check_64(String s,String i){
        if (!Objects.equals(s, i)){
            FirebaseCrash.report(new Exception("H_shod_" + getPackageName()));
            System.exit(0);
        }
    }

    public void Instagram(){
        (new DialogIns(Store.this)).showDialog();
    }

}
