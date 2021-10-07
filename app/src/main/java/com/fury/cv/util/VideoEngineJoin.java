package com.fury.cv.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.view.Join;
import com.fury.cv.view.ShareVideoActivity;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;

/**
 * Created by fury on 2/28/2017.
 */
public class VideoEngineJoin extends Service {

    FFmpeg ffmpeg,ffmpeg1,ffmpeg2;
    private NotificationCompat.Builder mBuilder;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String inputFileName,quality,path,b_S_t,b_S_c,b_F_t,b_E_t,b_F_c,b_E_c;
    String[] code_engine1,code_engine2,code_engine3;
    NotificationManager mNotifyManager;
    int work,format,one,gif,coinint;
    File root;
    String start ,format_ch;
    Boolean coin_alfa;
    String file1,file2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ffmpeg = FFmpeg.getInstance(VideoEngineJoin.this);
        ffmpeg1 = FFmpeg.getInstance(VideoEngineJoin.this);
        ffmpeg2  = FFmpeg.getInstance(VideoEngineJoin.this);

        loadFFMpegBinary();

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        work = one_play_preferences.getInt("work", 0);
        one = one_play_preferences.getInt("one", 1);
        gif = one_play_preferences.getInt("gif", 1);
        inputFileName = one_play_preferences.getString("inputFileName", "");
        quality = one_play_preferences.getString("quality", "29");
        path = one_play_preferences.getString("path", "");
        format_ch = one_play_preferences.getString("format_ch", "");
        format = one_play_preferences.getInt("format", 1);
        b_S_t = one_play_preferences.getString("b_S_t", "");
        b_F_c = one_play_preferences.getString("b_F_c", "");
        b_E_t = one_play_preferences.getString("b_E_t", "");
        b_E_c = one_play_preferences.getString("b_E_c", "");

        if (coin_alfa){
            coinint = 1000;
        }

        if (one == 1) {

            one_play_editor.putInt("one", 2);
            one_play_editor.apply();

            try {
                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test");
                if (!root.exists()) {
                    root.mkdirs();
                }
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("root"));
            }

            file1 = String.valueOf(new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int1.ts" ));
            file2 = String.valueOf(new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int2.ts" ));

            Engine1();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mNotifyManager.cancelAll();

        try {
            root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int1.ts");
            if (root.exists()) {
                root.delete();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("root delete"));
        }

        try {
            root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int2.ts");
            if (root.exists()) {
                root.delete();
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("root delete 2"));
        }

        try {
                    Join.end_activ();
                } catch (Exception e) {
            FirebaseCrash.report(new Exception("end_activ onDestroy"));
                }
    }


    private void Engine1() {
        code_engine1 = new String[]{"-i" ,quality,"-c","copy","-bsf:v", "h264_mp4toannexb" ,"-f","mpegts","-preset", "veryfast",file1};

        try {
                // to execute "ffmpeg -version" command you just need to pass "-version"
                ffmpeg.execute(code_engine1, new ExecuteBinaryResponseHandler() {

                    @Override
                    public void onStart() {
                        coinint = coinint - 1;
                        work = work + 1;
                        one_play_editor.putInt("COIN", coinint);
                        one_play_editor.putInt("work", work);
                        one_play_editor.apply();
                        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentTitle(b_S_t).setContentText("لطفا صبر کنید ...").setSmallIcon(R.drawable.play);
                        mBuilder.setProgress(100, 0, true);
                        int id = (int) System.currentTimeMillis();
                        mNotifyManager.notify(id, mBuilder.build());
                    }

                    @Override
                    public void onProgress(String message) {
                    }

                    @Override
                    public void onFailure(String message) {
                        coinint = coinint + 1;
                        one_play_editor.putInt("COIN", coinint);
                        one_play_editor.apply();
                        mNotifyManager.cancelAll();

                        root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int1.ts");
                        if (root.exists()) {
                            root.delete();
                        }

                        Join.text_help.setText(message);
                        NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentTitle("خطا").setContentText(b_F_c).setSmallIcon(R.drawable.play);
                        mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                        int id = (int) System.currentTimeMillis();
                        mNotifyManager.notify(id, mBuilder.build());

                        try {
                            Join.end_activ();
                        } catch (Exception e) {
                            FirebaseCrash.report(new Exception("OnFailure end_activ"));
                        }
                    }

                    @Override
                    public void onSuccess(String message) {

                        Engine2();

                    }
                    @Override
                    public void onFinish() {
                    }
                });
        } catch (FFmpegCommandAlreadyRunningException e) {
            try {
                Toast.makeText(getApplicationContext(), "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
            } catch (Exception e1) {
                FirebaseCrash.report(new Exception("not support"));
            }
        }
    }

    private void Engine2() {
        code_engine2 = new String[]{"-i" ,format_ch,"-c","copy","-bsf:v", "h264_mp4toannexb" ,"-f","mpegts","-preset", "veryfast",file2};

        try {
                // to execute "ffmpeg -version" command you just need to pass "-version"
                ffmpeg1.execute(code_engine2, new ExecuteBinaryResponseHandler() {

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onProgress(String message) {
                    }

                    @Override
                    public void onFailure(String message) {
                        coinint = coinint + 1;
                        one_play_editor.putInt("COIN", coinint);
                        one_play_editor.apply();
                        mNotifyManager.cancelAll();

                        try {
                            root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int2.ts");
                            if (root.exists()) {
                                root.delete();
                            }
                        } catch (Exception e) {
                            FirebaseCrash.report(new Exception("onFailure 2 root delete"));
                        }

                        Join.text_help.setText(message);
                        NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentTitle("خطا").setContentText(b_F_c).setSmallIcon(R.drawable.play);
                        mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                        int id = (int) System.currentTimeMillis();
                        mNotifyManager.notify(id, mBuilder.build());

                        try {
                            Join.end_activ();
                        } catch (Exception e) {
                            FirebaseCrash.report(new Exception("onFailure 2 end_activ"));
                        }
                    }

                    @Override
                    public void onSuccess(String message) {

                        Engine3();

                    }
                    @Override
                    public void onFinish() {
                    }
                });
        } catch (FFmpegCommandAlreadyRunningException e) {
            try {
                Toast.makeText(getApplicationContext(), "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
            } catch (Exception e1) {
                FirebaseCrash.report(new Exception("not work"));
            }
        }
    }

    private void Engine3() {

            code_engine3 = new String[]{"-i" ,"concat:"+file1+"|"+file2,"-c","copy","-bsf:a", "aac_adtstoasc" ,"-preset", "veryfast", path};

            try {
                    // to execute "ffmpeg -version" command you just need to pass "-version"
                    ffmpeg2.execute(code_engine3, new ExecuteBinaryResponseHandler() {

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onProgress(String message) {
                        }

                        @Override
                        public void onFailure(String message) {
                            coinint = coinint + 1;
                            one_play_editor.putInt("COIN", coinint);
                            one_play_editor.apply();
                            mNotifyManager.cancelAll();

                            try {
                                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int1.ts");
                                if (root.exists()) {
                                    root.delete();
                                }
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onFailure 3 root delete"));
                            }

                            try {
                                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int2.ts");
                                if (root.exists()) {
                                    root.delete();
                                }
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onFailure 3 root 2 delete"));
                            }

                            Join.text_help.setText(message);
                            NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            mBuilder = new NotificationCompat.Builder(getApplicationContext());
                            mBuilder.setContentTitle("خطا").setContentText(b_F_c).setSmallIcon(R.drawable.play);
                            mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                            int id = (int) System.currentTimeMillis();
                            mNotifyManager.notify(id, mBuilder.build());

                            try {
                                Join.end_activ();
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onFailure 3 end_activ"));
                            }

                        }

                        @Override
                        public void onSuccess(String message) {

                            mNotifyManager.cancelAll();

                            try {
                                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int1.ts");
                                if (root.exists()) {
                                    root.delete();
                                }
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onSuccess 3 root delete"));
                            }

                            try {
                                root = new File(Environment.getExternalStorageDirectory(), "Android/data/com.android.fury.cv/Test/int2.ts");
                                if (root.exists()) {
                                    root.delete();
                                }
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onSuccess 3 root 2 delete"));
                            }

                            Intent intent1 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                            intent1.setData(Uri.fromFile(new File(path)));
                            sendBroadcast(intent1);

                            try {
                                Join.in_activ();
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("onSuccess in_activ"));
                            }

                            NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            Intent notificationIntent = new Intent(VideoEngineJoin.this, ShareVideoActivity.class);
                            notificationIntent.putExtra("videofilename", path);
                            notificationIntent.putExtra("isfrommain", true);
                            PendingIntent pendingIntent = PendingIntent.getActivity(VideoEngineJoin.this, 0, notificationIntent, 0);
                            mBuilder = new NotificationCompat.Builder(getApplicationContext());
                            mBuilder.setContentTitle(b_E_t).setContentIntent(pendingIntent).setContentText(b_E_c).setSmallIcon(R.drawable.play);
                            mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                            int id = (int) System.currentTimeMillis();
                            mNotifyManager.notify(id, mBuilder.build());

                        }
                        @Override
                        public void onFinish() {
                        }
                    });
            } catch (FFmpegCommandAlreadyRunningException e) {
                try {
                    Toast.makeText(getApplicationContext(), "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
                } catch (Exception e1) {
                    FirebaseCrash.report(new Exception("not support 2"));
                }
            }

    }

    private void loadFFMpegBinary() {
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {mNotifyManager.cancelAll();

                    NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
                    mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                    int id = (int) System.currentTimeMillis();
                    mNotifyManager.notify(id, mBuilder.build());


                            try {
                                Join.end_activ();
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("not support 3 end_activ"));
                            }
                }
            });
        } catch (FFmpegNotSupportedException e) {
            NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
            mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
            mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
            int id = (int) System.currentTimeMillis();
            mNotifyManager.notify(id, mBuilder.build());

            try {
                Join.end_activ();
            } catch (Exception a) {
                FirebaseCrash.report(new Exception("not support 4 end_activ"));
            }
        }
        try {
            ffmpeg1.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {mNotifyManager.cancelAll();

                    NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
                    mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                    int id = (int) System.currentTimeMillis();
                    mNotifyManager.notify(id, mBuilder.build());


                            try {
                                Join.end_activ();
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("not support 5 end_activ"));
                            }
                }
            });
        } catch (FFmpegNotSupportedException e) {
            NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
            mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
            mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
            int id = (int) System.currentTimeMillis();
            mNotifyManager.notify(id, mBuilder.build());

            try {
                Join.end_activ();
            } catch (Exception a) {
                FirebaseCrash.report(new Exception("not support 6 end_activ"));
            }
        }
        try {
            ffmpeg2.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {mNotifyManager.cancelAll();

                    NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
                    mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                    int id = (int) System.currentTimeMillis();
                    mNotifyManager.notify(id, mBuilder.build());


                            try {
                                Join.end_activ();
                            } catch (Exception e) {
                                FirebaseCrash.report(new Exception("not support 7 end_activ"));
                            }
                }
            });
        } catch (FFmpegNotSupportedException e) {
            NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
            mBuilder.setContentTitle("خطا").setContentText("متاسفانه گوشی شما قادر به انجام کار نیست !").setSmallIcon(R.drawable.play);
            mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
            int id = (int) System.currentTimeMillis();
            mNotifyManager.notify(id, mBuilder.build());

            try {
                Join.end_activ();
            } catch (Exception a) {
                FirebaseCrash.report(new Exception("not support 8 end_activ"));
            }
        }
    }


}
