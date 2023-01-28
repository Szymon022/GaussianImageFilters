package org.openjfx.loader;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIO {

    public static WritableImage load(File image) {
        try {
            var bufferedImage = javax.imageio.ImageIO.read(image);
            var clonedImage = deepClone(bufferedImage);
            return SwingFXUtils.toFXImage(clonedImage, null);
        } catch (IOException e) {
            return null;
        }
    }

    public static void save(WritableImage image, File targetFile) {
        try {
            var bufferedImage = SwingFXUtils.fromFXImage(image, null);
            javax.imageio.ImageIO.write(bufferedImage, "png", targetFile);
        } catch (IOException e) {

        }
    }

    private static BufferedImage deepClone(BufferedImage bufferedImage) {
        var colorModel = bufferedImage.getColorModel();
        var isAlphaPremultiplied = bufferedImage.isAlphaPremultiplied();
        var raster = bufferedImage.getRaster();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
