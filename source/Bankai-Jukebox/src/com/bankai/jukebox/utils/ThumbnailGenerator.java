package com.bankai.jukebox.utils;

import com.bankai.jukebox.config.Constants;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ThumbnailGenerator {

    /**
     * Generates a thumbnail image for the given video file if it doesn't already exist.
     * If the thumbnail exists, it returns the existing thumbnail image.
     *
     * @param videoFile the video file from which to generate the thumbnail
     * @param width     the width of the thumbnail image
     * @param height    the height of the thumbnail image
     * @return the generated or existing thumbnail image as a BufferedImage
     */
    public BufferedImage generateThumbnail(File videoFile, int width, int height, String desiredThumbnailDirectory) {
        String thumbnailDirectory = desiredThumbnailDirectory == null ? Constants.APP_VIDEO_THUMBNAILS_DIRECTORY : desiredThumbnailDirectory;
        int frameNumber = 70;

        try {
            // Extracting thumbnail file name from video file name
            String thumbnailFileName = videoFile.getName().substring(0, videoFile.getName().lastIndexOf('.')) + ".png";
            File thumbnailFile = new File(thumbnailDirectory + File.separator + thumbnailFileName);

            // Create directory if it doesn't exist
            File directory = thumbnailFile.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory and its parents if missing
            }

            BufferedImage bufferedImage;

            // Check if thumbnail file already exists
            if (!thumbnailFile.exists()) {
                // Thumbnail doesn't exist, generate it
                Picture picture = FrameGrab.getFrameFromFile(videoFile, frameNumber);
                bufferedImage = AWTUtil.toBufferedImage(picture);

                // Write the generated thumbnail to file
                ImageIO.write(bufferedImage, "png", thumbnailFile);

                // Resize the thumbnail image
                bufferedImage = resize(bufferedImage, width, height);

                return bufferedImage;
            } else {
                // Resize the thumbnail image
                bufferedImage = resize(ImageIO.read(thumbnailFile), width, height);

                // Thumbnail already exists, return it
                return bufferedImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Resizes the given image to the specified width and height.
     *
     * @param image  the image to resize
     * @param width  the width to resize to
     * @param height the height to resize to
     * @return the resized image as a BufferedImage
     */
    private BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return resizedImage;
    }
}