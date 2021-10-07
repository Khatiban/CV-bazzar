package com.fury.cv.model;

import android.net.Uri;

public class VideoData {
    public String Duration;
    public Uri VideoUri;
    public long videoId;
    public String videoName;
    public String videoPath;

    public VideoData(String videoName, Uri VideoThumbUri, String VideoPath, String Duration) {
        this.videoName = videoName;
        this.VideoUri = VideoThumbUri;
        this.videoPath = VideoPath;
        this.Duration = Duration;
    }

    public VideoData(String videoName, Uri VideoThumbUri, String VideoPath) {
        this.videoName = videoName;
        this.VideoUri = VideoThumbUri;
        this.videoPath = VideoPath;
    }

    public String getName(){
        return videoName;
    }

}
