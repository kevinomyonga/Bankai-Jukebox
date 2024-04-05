package com.bankai.jukebox.experimental;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.component.AudioListPlayerComponent;
import uk.co.caprica.vlcj.player.list.PlaybackMode;

/**
 *
 * RTP Streaming using VLCJ
 * @author Kevin Omyonga
 *
 */
public class StreamRTP {
    private static final String[] factoryOptions = { "--no-sout-all", "--sout-shout-mp3", "--sout-keep" };
//    private static final String mediaOptions = ":sout=#rtp{dst=localhost,port=8000,mux=ts}";
//    private static final String mediaOptions = ":sout=#transcode{vcodec=none,acodec=mp3,ab=192,channels=2,samplerate=44100,scodec=none}:rtp{dst=localhost,port=8000,mux=ts}";

    /**
     *
     * @param serverAddress
     * @param serverPort
     * @return  mediaOptions for rtp stream wih vlc
     */
    private String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
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
        String address = "localhost";
//        String address = "localhost";
        int port = 8000;
        String mediaOptions = formatRtpStream(address, port);

        AudioListPlayerComponent component = new AudioListPlayerComponent(new MediaPlayerFactory(factoryOptions));
        component.mediaListPlayer().list().media().add(music, mediaOptions);
        component.mediaListPlayer().controls().setMode(PlaybackMode.LOOP);
        component.mediaListPlayer().controls().play();
        Thread.currentThread().join();

        System.out.println("Streaming started at rtp://" + address + ":" + port);
    }
}
