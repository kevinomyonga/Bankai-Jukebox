package com.bankai.jukebox.utils;

import com.bankai.jukebox.config.Constants;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class ThumbnailGenerator {

    public BufferedImage generateThumbnail(File videoFile, int width, int height) {
        int frameNumber = 70;

        try {
            Picture picture = FrameGrab.getFrameFromFile(
                    videoFile, frameNumber);
            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
//            ImageIO.write(bufferedImage, "png", new File(
//                    Constants.APP_VIDEOS_DIRECTORY + "/video-frame-" + UUID.randomUUID() + ".png"));

            bufferedImage = resize(bufferedImage, width, height);
//                    return thumbnail;

            return bufferedImage;
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

//    public BufferedImage generateThumbnail(File videoFile, int width, int height) throws IOException, FrameGrabber.Exception {
//        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile)) {
//            grabber.start();
//
//            Frame frame;
//            try (Java2DFrameConverter converter = new Java2DFrameConverter()) {
//                while ((frame = grabber.grabImage()) != null) {
//                    BufferedImage thumbnail = converter.getBufferedImage(frame);
//                    thumbnail = resize(thumbnail, width, height);
//                    return thumbnail;
//                }
//            }
//        }
//
//        return null;
//    }

    private BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return resizedImage;
    }
}