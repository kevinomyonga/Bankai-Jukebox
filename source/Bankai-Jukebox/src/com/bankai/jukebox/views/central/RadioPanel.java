package com.bankai.jukebox.views.central;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class RadioPanel extends JPanel {

    private JsonArray stations;

    int WIDTH = 150, HEIGHT = 150;

    private static final long serialVersionUID = 1L;

    /**
     * Native media player factory.
     */
    private MediaPlayerFactory mediaPlayerFactory;

    /**
     * Native media player.
     */
    private MediaPlayer mediaPlayer;

    private boolean record = false;

    public RadioPanel() {
        super();
        this.setLayout(new GridLayout(0, 4, 20, 20));

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

        loadStationsFromJSON();

        displayStations();

        setVisible(true);
    }

    private void loadStationsFromJSON() {
        try {
//            Object obj = JsonParser.parseReader(new FileReader(
//                    String.valueOf(RadioPanel.class.getClassLoader().getResource("data/stations/Kenya.json"))));
            Object obj = JsonParser.parseReader(new FileReader(
                    "/Users/kevinomyonga/Developer/Projects/IdeaProjects/Bankai-Jukebox/source/Bankai-Jukebox/resources/data/stations/Kenya.json"));
            stations = (JsonArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayStations() {
        for (Object obj : stations) {
            JsonObject station = (JsonObject) obj;
            String title = station.get("Title").getAsString();
            String description = station.get("Description").getAsString();
            String logoUrl = station.get("Logo").getAsString();
            String source1 = station.get("Source1").getAsString();

            // Create station panel
            JPanel stationPanel = new JPanel(new BorderLayout());
            stationPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Set panel size

            // Add image (assuming you have images corresponding to stations)
            // Add image (lazy loading with fade-in effect)
            ImageIcon defaultImageIcon = new ImageIcon(Objects.requireNonNull(
                    RadioPanel.class.getClassLoader().getResource("images/no-artwork.jpg")));
            JLabel imageLabel = new JLabel(defaultImageIcon);
            stationPanel.add(imageLabel, BorderLayout.CENTER);

            // Load image asynchronously
            new Thread(() -> {
                try {
                    BufferedImage image = loadImage(logoUrl);
                    if (image != null) {
                        ImageIcon scaledImageIcon = new ImageIcon(scaleImage(image, WIDTH, HEIGHT)); // Specify desired width and height
                        fadeImageIn(imageLabel, scaledImageIcon);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Add station name
            JLabel nameLabel = new JLabel(title);
            stationPanel.add(nameLabel, BorderLayout.SOUTH);

            // Add click listener
            stationPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Play the station stream
                    mediaPlayer.media().play(source1);
                    // Display dialog with station info
//                    JOptionPane.showMessageDialog(null, "Title: " + title + "\nDescription: " + description);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
//            stationPanel.addMouseListener(new StationClickListener(title, description, source1));

            // Add station panel to grid panel
            add(stationPanel);
        }
    }

    private void fadeImageIn(JLabel imageLabel, ImageIcon imageIcon) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            private float opacity = 0;

            @Override
            public void run() {
                opacity += 0.05f; // Adjust the fade-in speed here
                if (opacity >= 1f) {
                    opacity = 1f;
                    timer.cancel();
                }
                setOpacity(imageLabel, opacity);
                imageLabel.setIcon(imageIcon);
            }
        };
        timer.schedule(task, 0, 100); // Adjust the interval here
    }

    private void setOpacity(JLabel label, float opacity) {
        label.setIcon(new ImageIcon(makeImageTranslucent(((ImageIcon) label.getIcon()).getImage(), opacity)));
    }

    private BufferedImage makeImageTranslucent(Image image, float opacity) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }

    private BufferedImage loadImage(String imageUrl) throws IOException {
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
            return null;
        }
    }

    private Image scaleImage(Image image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

    private String[] getRecordMediaOptions(String address) {
        File file = getFile(address);
        StringBuilder sb = new StringBuilder(200);
        sb.append("sout=#transcode{acodec=mp3,channels=2,ab=192,samplerate=44100}:duplicate{dst=display,dst=std{access=file,mux=raw,dst=");
        sb.append(file.getPath());
        sb.append("}}");
        return new String[] {sb.toString()};
    }

    private File getFile(String address) {
        StringBuilder sb = new StringBuilder(100);
        try {
            URL url = new URL(address);
            sb.append(new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
            sb.append('-');
            sb.append(url.getHost().replace('.', '_'));
            sb.append('-');
            sb.append(url.getPort());
            sb.append(".mp3");

            File userHomeDirectory = new File(System.getProperty("user.home"));
            File saveDirectory = new File(userHomeDirectory, "vlcj-radio");
            if(!saveDirectory.exists()) {
                saveDirectory.mkdirs();
            }
            return new File(saveDirectory, sb.toString());
        }
        catch(MalformedURLException e) {
            throw new RuntimeException("Unable to create a URL for '" + address + "'");
        }
    }

}
