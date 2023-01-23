package org.openjfx.loader;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static WritableImage load(File image) {
        try {
            var bufferedImage = ImageIO.read(image);
            var clonedImage = deepClone(bufferedImage);
            return SwingFXUtils.toFXImage(clonedImage, null);
        } catch (IOException e) {
            return null;
        }
    }

    private static BufferedImage deepClone(BufferedImage bufferedImage) {
        var colorModel = bufferedImage.getColorModel();
        var isAlphaPremultiplied = bufferedImage.isAlphaPremultiplied();
        var raster = bufferedImage.getRaster();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
