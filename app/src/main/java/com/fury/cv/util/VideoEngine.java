package com.fury.cv.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.fury.cv.R;
import com.fury.cv.view.Create;
import com.fury.cv.view.Cut;
import com.fury.cv.view.DialogFollow;
import com.fury.cv.view.DialogNoTicket;
import com.fury.cv.view.DialogStar;
import com.fury.cv.view.Format;
import com.fury.cv.view.Gif;
import com.fury.cv.view.Join;
import com.fury.cv.view.Logo;
import com.fury.cv.view.Music;
import com.fury.cv.view.ShareVideoActivity;
import com.fury.cv.view.ViewVideo;
import com.fury.cv.view.Voice;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.util.Objects;

/**
 * Created by fury on 2/28/2017.
 */
public class VideoEngine extends Service {

    FFmpeg ffmpeg;
    private NotificationCompat.Builder mBuilder;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String inputFileName,quality,path,b_S_t,b_S_c,b_F_t,b_E_t,b_F_c,b_E_c,Pic,MP3Path,te1,te2,        te3,te4,        te5
    ,te6,        te7,te8,        te9,te10,        te11,te12,        te13,te14,        te15,te16,        te17,te18,        te19,te20,timepic;
    String[] code_engine;
    NotificationManager mNotifyManager;
    int code,format,one,gif,location,time,model,ho,ol,coinint,work;
    String start ,format_ch,over;
    Boolean coin_alfa;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        ffmpeg = FFmpeg.getInstance(VideoEngine.this);

        loadFFMpegBinary();

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();

        coin_alfa = one_play_preferences.getBoolean("COIN_Alfa", false);
        coinint = one_play_preferences.getInt("COIN", 0);
        work = one_play_preferences.getInt("work", 0);
        code = one_play_preferences.getInt("code", 1);
        one = one_play_preferences.getInt("one", 1);
        gif = one_play_preferences.getInt("gif", 1);
        location = one_play_preferences.getInt("location", 2);
        time = one_play_preferences.getInt("time", 1);
        timepic = String.valueOf(time);
        inputFileName = one_play_preferences.getString("inputFileName", "");
        quality = one_play_preferences.getString("quality", "29");
        path = one_play_preferences.getString("path", "");
        Pic = one_play_preferences.getString("Pic", "");
        format_ch = one_play_preferences.getString("format_ch", "");
        MP3Path = one_play_preferences.getString("MP3Path", "");
        te1  = one_play_preferences.getString("te1", "");
        te2  = one_play_preferences.getString("te2", "");
        te3  = one_play_preferences.getString("te3", "");
        te4  = one_play_preferences.getString("te4", "");
        te5  = one_play_preferences.getString("te5", "");
        te6  = one_play_preferences.getString("te6", "");
        te7  = one_play_preferences.getString("te7", "");
        te8  = one_play_preferences.getString("te8", "");
        te9  = one_play_preferences.getString("te9", "");
        te10 = one_play_preferences.getString("te10", "");
        te11 = one_play_preferences.getString("te11", "");
        te12 = one_play_preferences.getString("te12", "");
        te13 = one_play_preferences.getString("te13", "");
        te14 = one_play_preferences.getString("te14", "");
        te15 = one_play_preferences.getString("te15", "");
        te16 = one_play_preferences.getString("te16", "");
        te17 = one_play_preferences.getString("te17", "");
        te18 = one_play_preferences.getString("te18", "");
        te19 = one_play_preferences.getString("te19", "");
        te20 = one_play_preferences.getString("te20", "");
        format = one_play_preferences.getInt("format", 1);
        model = one_play_preferences.getInt("model", 1);
        ho = one_play_preferences.getInt("ho", 1);
        ol = one_play_preferences.getInt("ol", 1);
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

            if (code == 1) {
                code_engine = new String[]{"-y", "-i", inputFileName, "-vcodec", "libx264", "-crf", quality, "-acodec", "copy", "-preset", "veryfast", path};
            } else if (code == 2) {
                if (format == 1) {
                    code_engine = new String[]{"-i", inputFileName, "-preset", "veryfast", path};
                } else if (format == 2) {
                    code_engine = new String[]{"-i", inputFileName, "-target", format_ch, "-preset", "veryfast", path};
                } else if (format == 3) {
                    code_engine = new String[]{"-i", inputFileName, "-c:v", "libx264", "-preset", "veryfast", path};
                }
            }else if (code == 3) {
                if (gif == 1) {
                    code_engine = new String[]{"-i", inputFileName, "-vf", "scale=300:-1", "-gifflags","+transdiff", "-preset", "veryfast","-y", path};
                }else if (gif == 2) {
                    code_engine = new String[]{"-i", inputFileName, "-preset", "veryfast", path};
                }
            }else if (code == 4) {
                code_engine = new String[]{"-i", inputFileName, "-vn" , "-ar" ,"44100", "-ac" ,"2","-ab" ,format_ch ,"-preset", "veryfast", path};
                if (Objects.equals(quality, "wav")){
                    code_engine = new String[]{"-i", inputFileName, "-preset", "veryfast", path};
                }else if (Objects.equals(quality, "wma")){
                    code_engine = new String[]{"-i", inputFileName, "-preset", "veryfast", path};
                }
            }else if (code == 5) {
                code_engine = new String[]{"-i", quality ,"-i" ,inputFileName ,"-preset", "veryfast", path};
            }else if (code == 6) {
                code_engine = new String[]{"-ss" ,quality,"-i", inputFileName ,"-vcodec","copy","-acodec","copy","-t",format_ch,"-preset", "veryfast", path};
            }else if (code == 7) {
                code_engine = new String[]{"-f" ,"concat","-safe","0","-i", inputFileName ,"-vcodec","copy","-acodec","copy","-preset", "veryfast", path};
            }else if (code == 8) {
                if (time == 1) {
                    if (location == 1){
                        over = "main_w-overlay_w-10:10";
                    }else if (location == 2){
                        over = "10:10";
                    }else if (location == 3){
                        over = "main_w/2-overlay_w/2:main_h/2-overlay_h/2";
                    }else if (location == 4){
                        over = "10:main_h-overlay_h-10";
                    }else if (location == 5){
                        over = "main_w-overlay_w-10/2:main_h-overlay_h-10/2";
                    }else if (location == 6){
                        over = "main_w-overlay_w-10:10";
                    }

                    code_engine = new String[]{"-i" ,inputFileName,"-i",Pic,"-filter_complex", "[0:v][1:v]overlay="+over+":enable=between(t\\,"+quality+"\\,"+format_ch+")" ,"-acodec","copy","-preset", "veryfast", path};

                }else {
                    if (location == 1){
                        over = "overlay=main_w-overlay_w-10:10";
                    }else if (location == 2){
                        over = "overlay=10:10";
                    }else if (location == 3){
                        over = "overlay=main_w/2-overlay_w/2:main_h/2-overlay_h/2";
                    }else if (location == 4){
                        over = "overlay=10:main_h-overlay_h-10";
                    }else if (location == 5){
                        over = "overlay=main_w-overlay_w-10/2:main_h-overlay_h-10/2";
                    }else if (location == 6){
                        over = "overlay='if(gte(t,1), -w+(t-1)*200, NAN)':(main_h-overlay_h)/2";
                    }

                    code_engine = new String[]{"-i" ,inputFileName,"-i",Pic,"-filter_complex", over ,"-acodec","copy","-preset", "veryfast", path};

                }
            }else if (code == 9) {

                if (model == 1){

                    if (ol == 1){
                        //no music
                        code_engine = new String[]{"-framerate" ,"1/" + timepic,"-i", inputFileName ,"-c:v","libx264","-r","30","-pix_fmt", "yuv420p", path};
                    }else {
                        code_engine = new String[]{"-f" ,"concat","-i", inputFileName ,"-i",MP3Path,"-pix_fmt","yuv420p","-acodec","copy","-preset", "veryfast", path};
                    }

                }else if (model == 2){

                    if (ho == 20){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-loop" ,"1","-t",timepic,"-i", te11,"-loop" ,"1","-t",timepic,"-i", te12,
                                    "-loop" ,"1","-t",timepic,"-i", te13,"-loop" ,"1","-t",timepic,"-i", te14,
                                    "-loop" ,"1","-t",timepic,"-i", te15,"-loop" ,"1","-t",timepic,"-i", te16,
                                    "-loop" ,"1","-t",timepic,"-i", te17,"-loop" ,"1","-t",timepic,"-i", te18,
                                    "-loop" ,"1","-t",timepic,"-i", te19,"-loop" ,"1","-t",timepic,"-i", te20,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[5:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v5];[6:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v6];[7:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v7];[8:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v8];[9:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v9];[10:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v10];[11:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v11];[12:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v12];[13:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v13];[14:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v14];[15:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v15];[16:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v16];[17:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v17];[18v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v18];[19:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v19];[v0][v1][v2][v3][v4][v5][v6][v7][v8][v9][v10][v11][v12][v13][v14][v15][v16][v17][v18][v19][v20]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-loop" ,"1","-t",timepic,"-i", te11,"-loop" ,"1","-t",timepic,"-i", te12,
                                    "-loop" ,"1","-t",timepic,"-i", te13,"-loop" ,"1","-t",timepic,"-i", te14,
                                    "-loop" ,"1","-t",timepic,"-i", te15,"-loop" ,"1","-t",timepic,"-i", te16,
                                    "-loop" ,"1","-t",timepic,"-i", te17,"-loop" ,"1","-t",timepic,"-i", te18,
                                    "-loop" ,"1","-t",timepic,"-i", te19,"-loop" ,"1","-t",timepic,"-i", te20,
                                    "-i",MP3Path,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[5:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v5];[6:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v6];[7:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v7];[8:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v8];[9:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v9];[10:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v10];[11:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v11];[12:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v12];[13:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v13];[14:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v14];[15:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v15];[16:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v16];[17:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v17];[18v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v18];[19:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v19];[v0][v1][v2][v3][v4][v5][v6][v7][v8][v9][v10][v11][v12][v13][v14][v15][v16][v17][v18][v19]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }else if (ho == 10){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[5:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v5];[6:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v6];[7:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v7];[8:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v8];[9:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v9];[v0][v1][v2][v3][v4][v5][v6][v7][v8][v9]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-i",MP3Path,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[5:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v5];[6:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v6];[7:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v7];[8:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v8];[9:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v9];[v0][v1][v2][v3][v4][v5][v6][v7][v8][v9]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }else if (ho == 5){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[v0][v1][v2][v3][v4]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,
                                    "-i",MP3Path,
                                    "-filter_complex","[0:v]fade=t=out:st=4:d=1[v0];[1:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v1];[2:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v2];[3:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v3];[4:v]fade=t=in:st=0:d=1,fade=t=out:st=4:d=1[v4];[v0][v1][v2][v3][v4]concat=n=5:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }

                }else if (model == 3){

                    if (ho == 20){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-loop" ,"1","-t",timepic,"-i", te11,"-loop" ,"1","-t",timepic,"-i", te12,
                                    "-loop" ,"1","-t",timepic,"-i", te13,"-loop" ,"1","-t",timepic,"-i", te14,
                                    "-loop" ,"1","-t",timepic,"-i", te15,"-loop" ,"1","-t",timepic,"-i", te16,
                                    "-loop" ,"1","-t",timepic,"-i", te17,"-loop" ,"1","-t",timepic,"-i", te18,
                                    "-loop" ,"1","-t",timepic,"-i", te19,"-loop" ,"1","-t",timepic,"-i", te20,
                                    "-filter_complex","[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[5:v][4:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b5v];[6:v][5:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b6v];[7:v][6:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b7v];[8:v][7:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b8v];[9:v][8:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b9v];[10:v][9:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b10v];[11:v][10:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b11v];[12:v][11:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b12v];[13:v][12:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b13v];[14:v][13:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b14v];[15:v][14:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b15v];[16:v][15:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b16v];[17:v][16:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b17v];[18:v][17:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b18v];[19:v][18:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b19v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v][b5v][5:v][b6v][6:v][b7v][7:v][b8v][8:v][b9v][9:v][b10v][10:v][b11v][11:v][b12v][12:v][b13v][13:v][b14v][14:v][b15v][15:v][b16v][16:v][b17v][17:v][b18v][18:v][b19v][19:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-loop" ,"1","-t",timepic,"-i", te11,"-loop" ,"1","-t",timepic,"-i", te12,
                                    "-loop" ,"1","-t",timepic,"-i", te13,"-loop" ,"1","-t",timepic,"-i", te14,
                                    "-loop" ,"1","-t",timepic,"-i", te15,"-loop" ,"1","-t",timepic,"-i", te16,
                                    "-loop" ,"1","-t",timepic,"-i", te17,"-loop" ,"1","-t",timepic,"-i", te18,
                                    "-loop" ,"1","-t",timepic,"-i", te19,"-loop" ,"1","-t",timepic,"-i", te20,
                                    "-i",MP3Path,
                                    "-filter_complex","[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[5:v][4:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b5v];[6:v][5:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b6v];[7:v][6:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b7v];[8:v][7:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b8v];[9:v][8:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b9v];[10:v][9:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b10v];[11:v][10:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b11v];[12:v][11:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b12v];[13:v][12:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b13v];[14:v][13:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b14v];[15:v][14:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b15v];[16:v][15:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b16v];[17:v][16:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b17v];[18:v][17:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b18v];[19:v][18:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b19v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v][b5v][5:v][b6v][6:v][b7v][7:v][b8v][8:v][b9v][9:v][b10v][10:v][b11v][11:v][b12v][12:v][b13v][13:v][b14v][14:v][b15v][15:v][b16v][16:v][b17v][17:v][b18v][18:v][b19v][19:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }else if (ho == 10){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-filter_complex", "[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[5:v][4:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b5v];[6:v][5:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b6v];[7:v][6:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b7v];[8:v][7:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b8v];[9:v][8:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b9v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v][b5v][5:v][b6v][6:v][b7v][7:v][b8v][8:v][b9v][9:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,"-loop" ,"1","-t",timepic,"-i", te6,
                                    "-loop" ,"1","-t",timepic,"-i", te7,"-loop" ,"1","-t",timepic,"-i", te8,
                                    "-loop" ,"1","-t",timepic,"-i", te9,"-loop" ,"1","-t",timepic,"-i", te10,
                                    "-i",MP3Path,
                                    "-filter_complex", "[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[5:v][4:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b5v];[6:v][5:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b6v];[7:v][6:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b7v];[8:v][7:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b8v];[9:v][8:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b9v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v][b5v][5:v][b6v][6:v][b7v][7:v][b8v][8:v][b9v][9:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }else if (ho == 5){
                        if (ol == 1){
                            //no music
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,
                                    "-filter_complex", "[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-acodec","copy","-preset", "veryfast", path};
                        }else {
                            code_engine = new String[]{"-loop" ,"1","-t",timepic,"-i", te1,"-loop" ,"1","-t",timepic,"-i", te2,
                                    "-loop" ,"1","-t",timepic,"-i", te3,"-loop" ,"1","-t",timepic,"-i", te4,
                                    "-loop" ,"1","-t",timepic,"-i", te5,
                                    "-i",MP3Path,
                                    "-filter_complex", "[1:v][0:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b1v];[2:v][1:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b2v];[3:v][2:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b3v];[4:v][3:v]blend=all_expr='A*(if(gte(T,0.5),1,T/0.5))+B*(1-(if(gte(T,0.5),1,T/0.5)))'[b4v];[0:v][b1v][1:v][b2v][2:v][b3v][3:v][b4v][4:v]concat=n=9:v=1:a=0,format=yuv420p[v]",
                                    "-map","[v]","-vsync","vfr","-acodec","copy","-preset", "veryfast", path};
                        }
                    }

                }

            }

            try {
                if (start == null) {
                    start = "start";


                        // to execute "ffmpeg -version" command you just need to pass "-version"
                        ffmpeg.execute(code_engine, new ExecuteBinaryResponseHandler() {

                            @Override
                            public void onStart() {
                                try {
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
                                } catch (Exception e) {
                                    FirebaseCrash.report(new Exception("mNotiMang start"));
                                }
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
                                NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                mBuilder = new NotificationCompat.Builder(getApplicationContext());
                                mBuilder.setContentTitle("خطا").setContentText(b_F_c).setSmallIcon(R.drawable.play);
                                mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                                int id = (int) System.currentTimeMillis();
                                mNotifyManager.notify(id, mBuilder.build());
                                FirebaseCrash.report(new Exception("onFailure " + one + " /// " + message));
                                try {
                                    if (code == 1) {
                                        try {
                                            ViewVideo.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 2) {
                                        try {
                                            Format.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 3) {
                                        try {
                                            Gif.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 4) {
                                        try {
                                            Music.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 5) {
                                        try {
                                            Voice.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 6) {
                                        try {
                                            Cut.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 7) {
                                        try {
                                            Join.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 8) {
                                        try {
                                            Logo.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 9) {
                                        try {
                                            Create.end_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (Exception e) {
                                    FirebaseCrash.report(new Exception("onFauled end_activ"));
                                }

                                startService(new Intent(VideoEngine.this, serend.class));
                            }

                            @Override
                            public void onSuccess(String message) {

                                mNotifyManager.cancelAll();
                                Intent intent1 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                                intent1.setData(Uri.fromFile(new File(path)));
                                sendBroadcast(intent1);


                                try {
                                    if (code == 1) {
                                        try {
                                            ViewVideo.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 2) {
                                        try {
                                            Format.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 3) {
                                        try {
                                            Gif.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 4) {
                                        try {
                                            Music.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 5) {
                                        try {
                                            Voice.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 6) {
                                        try {
                                            Cut.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 7) {
                                        try {
                                            Join.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (code == 8) {
                                        try {
                                            Logo.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else if (code == 9) {
                                        try {
                                            Create.in_activ();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (Exception e) {
                                    FirebaseCrash.report(new Exception("onSeccess in_activ"));
                                }

                                NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                Intent notificationIntent = new Intent(VideoEngine.this, ShareVideoActivity.class);
                                notificationIntent.putExtra("videofilename", path);
                                notificationIntent.putExtra("isfrommain", true);
                                PendingIntent pendingIntent = PendingIntent.getActivity(VideoEngine.this, 0, notificationIntent, 0);
                                mBuilder = new NotificationCompat.Builder(getApplicationContext());
                                mBuilder.setContentTitle(b_E_t).setContentIntent(pendingIntent).setContentText(b_E_c).setSmallIcon(R.drawable.play);
                                mBuilder.setVibrate(new long[]{200, 200, 200, 200, 200});
                                int id = (int) System.currentTimeMillis();
                                mNotifyManager.notify(id, mBuilder.build());
                                try {
                                    FirebaseCrash.report(new Exception("onSeccess " + one));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                try {
                                    startService(new Intent(VideoEngine.this, serend.class));
                                } catch (Exception e) {
                                    FirebaseCrash.report(new Exception("onSeccess " + "start serend"));
                                }
                            }
                            @Override
                            public void onFinish() {
                            }
                        });

                }
            } catch (FFmpegCommandAlreadyRunningException e) {
                try {
                    Toast.makeText(getApplicationContext(), "متاسفانه گوشی شما قادر به انجام کار نیست !", Toast.LENGTH_LONG).show();
                } catch (Exception e1) {
                    FirebaseCrash.report(new Exception("error ffmpge"));
                }
            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mNotifyManager.cancelAll();

        try {
            if (code == 1) {
                try {
                    ViewVideo.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (code == 2) {
                try {
                    Format.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 3) {
                try {
                    Gif.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 4) {
                try {
                    Music.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 5) {
                try {
                    Voice.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 6) {
                try {
                    Cut.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 7) {
                try {
                    Join.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 8) {
                try {
                    Logo.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (code == 9) {
                try {
                    Create.end_activ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("onDesroi"));
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
                        if (code == 1) {
                            try {
                                ViewVideo.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (code == 2) {
                            try {
                                Format.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 3) {
                            try {
                                Gif.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 4) {
                            try {
                                Music.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 5) {
                            try {
                                Voice.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 6) {
                            try {
                                Cut.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 7) {
                            try {
                                Join.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 8) {
                            try {
                                Logo.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (code == 9) {
                            try {
                                Create.end_activ();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        FirebaseCrash.report(new Exception("not ffmpeg end"));
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
                if (code == 1) {
                    ViewVideo.end_activ();
                } else if (code == 2) {
                    Format.end_activ();
                }else if (code == 3) {
                    Gif.end_activ();
                }else if (code == 4) {
                    Music.end_activ();
                }else if (code == 5) {
                    Voice.end_activ();
                }else if (code == 6) {
                    Cut.end_activ();
                }else if (code == 7) {
                    Join.end_activ();
                }else if (code == 8) {
                    Logo.end_activ();
                }else if (code == 9) {
                    Create.end_activ();
                }
            } catch (Exception a) {
                FirebaseCrash.report(new Exception("not ffmpeg end2"));
            }
        }
    }

}
