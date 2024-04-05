package com.bankai.jukebox.experimental;

import com.bankai.jukebox.config.Constants;

import java.io.File;

/**
 *
 * RTP Streaming Experimental app
 * @author Kevin Omyonga
 *
 */
public class JukeboxStreaming {
    /**
     * Main execution
     * @param args Arguments
     */
    public static void main(String[] args) {
        try {

            System.out.println("Begin Streaming APP");
            StreamHttp http = new StreamHttp();
            //rtp.start("YOUR_LOCAL_IP_HERE", PORT, "sample.mp3");
            http.start(Constants.APP_MUSIC_DIRECTORY + File.separator + "07 faint.mp3");
            System.out.println("End Streaming APP");

//            System.out.println("Begin Streaming APP");
//            StreamRTP rtp = new StreamRTP();
//            //rtp.start("YOUR_LOCAL_IP_HERE", PORT, "sample.mp3");
////            rtp.start("192.168.100.2", 8000, Constants.APP_MUSIC_DIRECTORY + File.separator + "07 faint.mp3");
//            rtp.start(Constants.APP_MUSIC_DIRECTORY + File.separator + "07 faint.mp3");
//            System.out.println("End Streaming APP");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
