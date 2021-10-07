package com.fury.cv.util;

public class TimeUtils {

    public class MilliSeconds {
        public static final int ONE_HOUR = 3600000;
        public static final int ONE_MINUTE = 60000;
        public static final int ONE_SECOND = 1000;
    }

    static String finalTimerString;
    static String secondsString;

    public static String toFormattedTime(int time) {

        try {
            String out;
            long hours = time / 3600000;
            long remaining_minutes = (time - (3600000 * hours)) / 60000;
            String minutes = String.valueOf(remaining_minutes);
            if (minutes.equals(Integer.valueOf(0))) {
                minutes = "00";
            }
            String seconds = String.valueOf((time - (3600000 * hours)) - (60000 * remaining_minutes));
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

        /*int remainingTime = time;
        int hours = remainingTime / MilliSeconds.ONE_HOUR;
        remainingTime -= MilliSeconds.ONE_HOUR * hours;
        int minutes = (int) (remainingTime % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (remainingTime - ((remainingTime / MilliSeconds.ONE_MINUTE) * MilliSeconds.ONE_MINUTE)) / MilliSeconds.ONE_SECOND;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds)});
        }
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(minutes), Integer.valueOf(seconds)});*/
    }
}
