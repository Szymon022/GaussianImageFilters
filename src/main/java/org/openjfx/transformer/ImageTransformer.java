package org.openjfx.transformer;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.openjfx.geometry.Area;
import org.openjfx.kernels.Kernel;
import org.openjfx.loader.ImageLoader;

import java.io.File;

public class ImageTransformer {

    private WritableImage image;
    private Kernel kernel;

    public ImageTransformer(ImageTransformer.Builder imageTransformerBuilder) {
        this.image = ImageLoader.load(imageTransformerBuilder.imageFile);
        this.kernel = imageTransformerBuilder.kernel;
    }

    public Image transformArea(Area area) {
        var pixelReader = image.getPixelReader();
        var pixelWriter = image.getPixelWriter();
        area.forEach(point -> {
            var transformedPixelColor = computeColor(pixelReader, point);
            pixelWriter.setColor((int) point.getX(), (int) point.getY(), transformedPixelColor);
        });
        return image;
    }

    private Color computeColor(PixelReader pixelReader, Point2D point) {
        var red = 0.0;
        var green = 0.0;
        var blue = 0.0;
        final var offset = kernel.getOffset();
        var i = 0;
        var j = 0;
        for (int x = (int) (point.getX() - offset); x <= point.getX() + offset; x++) {
            for (int y = (int) (point.getY() - offset); y <= point.getY() + offset; y++) {
                var pixel = pixelReader.getColor(x, y);
                var multiplier = kernel.getValue(i, j);
                red += pixel.getRed() * multiplier;
                green += pixel.getGreen() * multiplier;
                blue += pixel.getBlue() * multiplier;
                j++;
            }
            i++;
        }
        var divider = kernel.getDivider();
        var centerPointColor = pixelReader.getColor((int) point.getX(), (int) point.getY());
        var normalizedRedColor = coerceIn(red / divider, 0, 1);
        var normalizedGreenColor = coerceIn(green / divider, 0, 1);
        var normalizedBlueColor = coerceIn(blue / divider, 0, 1);
        return new Color(normalizedRedColor, normalizedGreenColor, normalizedBlueColor, centerPointColor.getOpacity());
    }

    private double coerceIn(double value, double minValue, double maxValue) {
        if (value < minValue) return minValue;
        return Math.min(value, maxValue);
    }

    public static class Builder {

        private File imageFile;
        private Kernel kernel;

        public Builder() {
            imageFile = null;
            kernel = null;
        }

        public Builder setImage(File file) {
            imageFile = file;
            return this;
        }

        public Builder setKernel(Kernel kernel) {
            this.kernel = kernel;
            return this;
        }

        public ImageTransformer build() {
            return new ImageTransformer(this);
        }
    }
}
