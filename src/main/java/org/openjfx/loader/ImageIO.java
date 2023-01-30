package org.openjfx.loader;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

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

    public static Image copy(Image image) {
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();
        var pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        var pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var color = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, color);
            }
        }
        return writableImage;
    }

    private static BufferedImage deepClone(BufferedImage bufferedImage) {
        var colorModel = bufferedImage.getColorModel();
        var isAlphaPremultiplied = bufferedImage.isAlphaPremultiplied();
        var raster = bufferedImage.getRaster();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
