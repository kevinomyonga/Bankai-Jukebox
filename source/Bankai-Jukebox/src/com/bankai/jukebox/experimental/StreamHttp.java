package com.bankai.jukebox.experimental;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

/**
 *
 * HTTP Streaming using VLCJ
 * @author Kevin Omyonga
 *
 */
public class StreamHttp {

    private static String formatHttpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
        sb.append("dst=");
        sb.append(serverAddress);
        sb.append(':');
        sb.append(serverPort);
        sb.append("}}");
        return sb.toString();
    }

    /**
     * Start streaming of music by adding music to the playList
     *
     //     * @param address
     //     * @param port
     * @throws Exception
     */
    public void start(String music) throws Exception {
//        String address = "192.168.100.2";
        String address = "10.66.21.111";
        int port = 8000;
        String mediaOptions = formatHttpStream(address, port);

        System.out.println("Streaming '" + music + "' to '" + mediaOptions + "'");

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
        mediaPlayer.media().play(music, mediaOptions);

        // Don't exit
        Thread.currentThread().join();

        System.out.println("Streaming started at http://" + address + ":" + port);
    }
}
