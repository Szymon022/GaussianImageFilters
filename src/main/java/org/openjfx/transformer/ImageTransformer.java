package org.openjfx.transformer;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.openjfx.area.Area;
import org.openjfx.kernels.Kernel;
import org.openjfx.loader.ImageIO;

import java.io.File;

public class ImageTransformer {

    private WritableImage image;
    private Image readOnlyImage;
    private Kernel kernel;
    private double width;
    private double height;

    public ImageTransformer(ImageTransformer.Builder imageTransformerBuilder) {
        this.image = ImageIO.load(imageTransformerBuilder.imageFile);
        this.readOnlyImage = ImageIO.load(imageTransformerBuilder.imageFile);
        this.kernel = imageTransformerBuilder.kernel;
        assert image != null;
        width = image.getWidth();
        height = image.getHeight();
    }

    public Image transformArea(Area area) {
        var pixelReader = readOnlyImage.getPixelReader();
        var pixelWriter = image.getPixelWriter();
        area.forEach(point -> {
            if (!doesPointFitInImageBounds(point)) return;
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
        var kernelX = 0;
        var kernelY = 0;
        var pointX = (int) point.getX();
        var pointY = (int) point.getY();
        for (int y = (pointY - offset); y <= pointY + offset; y++) {
            kernelY = 0;
            for (int x = (pointX - offset); x <= pointX + offset; x++) {
                if (doesPointFitInImageBounds(x, y)) {
                    var pixel = pixelReader.getColor(x, y);
                    var multiplier = kernel.getValue(kernelX, kernelY);
                    red += pixel.getRed() * multiplier;
                    green += pixel.getGreen() * multiplier;
                    blue += pixel.getBlue() * multiplier;
                }
                kernelY++;
            }
            kernelX++;
        }
        var divider = kernel.getDivider();
        var centerPointColor = pixelReader.getColor(pointX, pointY);
        var transformedRed = coerceIn(red / divider, 0, 1);
        var transformedGreen = coerceIn(green / divider, 0, 1);
        var transformedBlue = coerceIn(blue / divider, 0, 1);
        return new Color(transformedRed, transformedGreen, transformedBlue, centerPointColor.getOpacity());
    }

    private double coerceIn(double value, double minValue, double maxValue) {
        if (value < minValue) return minValue;
        return Math.min(value, maxValue);
    }

    private boolean doesPointFitInImageBounds(Point2D point) {
        return doesPointFitInImageBounds((int) point.getX(), (int) point.getY());
    }

    private boolean doesPointFitInImageBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
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

        public boolean hasAllDataSet() {
            return imageFile != null && kernel != null;
        }

        public ImageTransformer build() {
            return new ImageTransformer(this);
        }
    }
}
