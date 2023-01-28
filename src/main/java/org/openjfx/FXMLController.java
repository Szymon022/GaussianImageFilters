package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.openjfx.drawing.*;
import org.openjfx.kernels.*;
import org.openjfx.loader.ImageLoader;
import org.openjfx.transformer.ImageTransformer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Canvas canvas;
    @FXML
    private Slider radiusSlider;
    @FXML
    private VBox sliderLayout;
    @FXML
    private Button clearPolygonButton;

    private ImageTransformer.Builder imageTransformerBuilder;
    private FileChooser fileChooser;
    private AreaSelector areaSelector;
    private AreaPainter areaPainter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Png", "*.png"));
        imageTransformerBuilder = new ImageTransformer.Builder();
        areaPainter = new AreaPainter(canvas.getGraphicsContext2D());
        areaSelector = new RectangularAreaSelector(
                0,
                0,
                (int) canvas.getWidth(),
                (int) canvas.getHeight()
        );
        initListeners();
        onWholeImageRadioButtonSelected();
    }

    @FXML
    protected void onWholeImageRadioButtonSelected() {
        sliderLayout.setDisable(true);
        clearPolygonButton.setDisable(true);
        areaSelector = new RectangularAreaSelector(
                0,
                0,
                (int) canvas.getWidth(),
                (int) canvas.getHeight()
        );
        areaPainter.paint(areaSelector.get());
    }

    @FXML
    protected void onCircularAreaRadioButtonSelected() {
        sliderLayout.setDisable(false);
        clearPolygonButton.setDisable(true);
        areaSelector = new CircleAreaSelector();
        var circularAreaSelector = (CircleAreaSelector) areaSelector;
        circularAreaSelector.addPoint(0, 0);
        circularAreaSelector.setRadius(100);
        areaPainter.paint(circularAreaSelector.get());
    }

    @FXML
    protected void onPolygonalAreaRadioButtonSelected() {
        sliderLayout.setDisable(true);
        clearPolygonButton.setDisable(false);
        areaSelector = new PolygonalAreaSelector();
        areaPainter.paint(areaSelector.get());
    }

    @FXML
    protected void onIdentityMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new IdentityKernel());
    }

    @FXML
    protected void on3x3BlurMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new GaussianBlur3x3Kernel());
    }

    @FXML
    protected void on5x5BlurMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new GaussianBlur5x5Kernel());
    }

    @FXML
    protected void on3x3SharpenMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new Sharpen3x3Kernel());
    }

    @FXML
    protected void onEmbossMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new EmbossKernel());
    }

    @FXML
    protected void onEdgeDetectionMatrixRadioButtonSelected() {
        imageTransformerBuilder.setKernel(new EdgeDetectionKernel());
    }

    @FXML
    protected void onApplyButtonClick() {
        if (imageTransformerBuilder.hasAllDataSet()) {
            ImageTransformer transformer = imageTransformerBuilder.build();
            Image transformedImage = transformer.transformArea(areaSelector.get());
            imageView.setImage(transformedImage);
        }
    }

    @FXML
    protected void onClearPolygonButtonClick() {
        areaSelector = new PolygonalAreaSelector();
        areaPainter.paint(areaSelector.get());
    }

    @FXML
    protected void onSaveAsMenuItemClick() {
    }

    @FXML
    protected void onOpenMenuItemClick() {
        Stage stage = new Stage();
        File image = fileChooser.showOpenDialog(stage);
        if (image != null) {
            imageTransformerBuilder.setImage(image);
            var loadedImage = ImageLoader.load(image);
            imageView.setImage(loadedImage);
            assert loadedImage != null;
            var height = loadedImage.getHeight();
            var width = loadedImage.getWidth();
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            canvas.setHeight(height);
            canvas.setWidth(width);
        }
    }

    @FXML
    protected void onResetMenuItemClick() {
    }

    private void initListeners() {
        canvas.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                areaSelector.moveTo((int) event.getX(), (int) event.getY());
                areaPainter.paint(areaSelector.get());
            }
        });
        canvas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                areaSelector.addPoint((int) event.getX(), (int) event.getY());
                areaPainter.paint(areaSelector.get());
            }
        });
        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ((CircleAreaSelector) areaSelector).setRadius(newValue.intValue());
            areaPainter.paint(areaSelector.get());
        });
    }
}