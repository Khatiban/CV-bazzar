package com.fury.cv.util;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileUtils {

    static List<String> fileList;
    /* renamed from: com.video.compressop.util.FileUtils.1 */
    static class C10411 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10411(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Compression Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }

    static class C10412 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;
        private final /* synthetic */ String format;

        C10412(String str,String form) {
            this.val$fileName = str;
            format = form;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Compression Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + format);
        }
    }

    static class C10413 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10413(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Create GIF) " + new SimpleDateFormat("mm_ss").format(new Date()) +".gif");
        }
    }

    static class C10414 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;
        private final /* synthetic */ String format;

        C10414(String str,String form) {
            this.val$fileName = str;
            format = form;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Music) " + new SimpleDateFormat("mm_ss").format(new Date()) + format);
        }
    }

    static class C10415 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10415(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Sound Investment) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }

    static class C10416 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10416(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Cut Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }

    static class C10417 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10417(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Join Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }

    static class C10418 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10418(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }
    static class C10419 implements FilenameFilter {
        private final /* synthetic */ String val$fileName;

        C10419(String str) {
            this.val$fileName = str;
        }

        public boolean accept(File dir, String filename) {
            return filename != null && filename.startsWith(val$fileName) && filename.endsWith(" CV (Best App Create Video) " + new SimpleDateFormat("mm_ss").format(new Date()) + ".mp4");
        }
    }

    public static String getStringFilterEnd(String org, int start, String end) {
        String endst = "";
        int endindex = 0;
        try {
            endindex = org.indexOf(end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        endst = org.substring(start, endindex);
        return endst;
    }

    public static String getTargetFileName(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
           fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Compressed").getAbsoluteFile().list(new C10411(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Compression Video) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Compressed",targetFileName).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameGif(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Gif").getAbsoluteFile().list(new C10413(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Create GIF) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".gif";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Gif", targetFileName).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameFormat(String inputFileName,int m,String format) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Format").getAbsoluteFile().list(new C10412(name,format)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Change Format) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + format;
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Format", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameMusic(String inputFileName,int m,String format) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Music").getAbsoluteFile().list(new C10414(name,format)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Music) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + format;
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Music", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameVoice(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Sound").getAbsoluteFile().list(new C10415(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Sound Investment) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Sound", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameCut(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Cut").getAbsoluteFile().list(new C10416(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Cut Video) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Cut", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameJoin(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = null;
        try {
            name = getStringFilterEnd(fileName, 1, ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Join").getAbsoluteFile().list(new C10417(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Join Video) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Join", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameLogo(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Logo").getAbsoluteFile().list(new C10418(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Video) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Logo", targetFileName ).getPath();
                }
            }
        }
    }

    public static String getTargetFileNameCreate(String inputFileName,int m) {
        String fileName = new File(inputFileName).getAbsoluteFile().getName();
        String name = getStringFilterEnd(fileName, 1, ".");
        int count = 0;
        if (m == 1){
            fileList = Arrays.asList(new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Create").getAbsoluteFile().list(new C10419(name)));
        }
        while (true) {
            String stringBuilder = " CV (Best App Create video) " + new SimpleDateFormat("mm_ss").format(new Date());
            String targetFileName = name + stringBuilder + ".mp4";
            if (!fileList.contains(targetFileName)) {
                if (m == 1){
                    return new File(Environment.getExternalStorageDirectory() + "/CV" + "/" + "Create", targetFileName ).getPath();
                }
            }
        }
    }

    public static String convertDuration(long duration) {
        try {
            String out;
            long hours = duration / 3600000;
            long remaining_minutes = (duration - (3600000 * hours)) / 60000;
            String minutes = String.valueOf(remaining_minutes);
            if (minutes.equals(Integer.valueOf(0))) {
                minutes = "00";
            }
            String seconds = String.valueOf((duration - (3600000 * hours)) - (60000 * remaining_minutes));
            if (seconds.length() < 2) {
                seconds = "00";
            } else {
                seconds = seconds.substring(0, 2);
            }
            if (hours > 0) {
                out = new StringBuilder(String.valueOf(hours)).append(":").append(minutes).append(":").append(seconds).toString();
            } else {
                out = new StringBuilder(String.valueOf(minutes)).append(":").append(seconds).toString();
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
