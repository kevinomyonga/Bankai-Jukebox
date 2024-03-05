//package com.bankai.jukebox.utils;
//
//import org.bytedeco.javacv.*;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class ThumbnailGenerator {
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
//
//    private BufferedImage resize(BufferedImage image, int width, int height) {
//        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        resizedImage.getGraphics().drawImage(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
//        return resizedImage;
//    }
//}